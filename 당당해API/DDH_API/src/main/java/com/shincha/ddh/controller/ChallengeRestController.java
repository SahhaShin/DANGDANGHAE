package com.shincha.ddh.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.One_day_exercise;
import com.shincha.ddh.model.dto.Team;
import com.shincha.ddh.model.dto.Users;
import com.shincha.ddh.model.dto.Users_has_challenge;
import com.shincha.ddh.model.service.ChallengeService;
import com.shincha.ddh.model.service.UserService;

@RestController
@RequestMapping("/api-challenge")
public class ChallengeRestController {

	@Autowired
	ChallengeService challengeService;

	@Autowired
	private UserService userService;

	@Autowired
	ResourceLoader resLoader;

	// 1. 전체 챌린지 가져오기
	@GetMapping("/challengeList")
	public ResponseEntity<?> doChallengeList() {
		List<Challenge> challengeList = challengeService.selectChallengeAll();

		if (challengeList != null && challengeList.size() > 0) {
			return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 2. 챌린지 만들기 -> 팀 추가하기 (이미지 있음)
	// 이미지를 넣으려면 json형식으로 못보냄
	@PostMapping("/makeChallenge")
	public ResponseEntity<?> doMakeChallenge(@RequestBody Challenge info) throws IOException {

		// 방장이 돈이 안되면 신청 못하게 해야함
		Users organizer = userService.selectById2(info.getOrganizer());// 방장
		if (info.getPoint() > organizer.getPoint())
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		// 돈이 되면 고

		// 파일 등록
//		org.springframework.core.io.Resource res = resLoader.getResource("resouces");
//
//		if (file != null && file.getSize() > 0) {
//			if (!res.getFile().exists())
//				res.getFile().mkdir();
//
//			String orgImg = file.getOriginalFilename();
//			info.setImage(System.currentTimeMillis() + "_" + orgImg);
//			file.transferTo(new File(res.getFile().getCanonicalPath() + "/" + info.getImage()));
//		}

		// 챌린지 만들기
		int result1 = challengeService.insertChallenge(info);
		if (result1 < 0) {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}

		// 만들어진 챌린지 가져와서 챌린지 no 확인하기
		// 만들어진 챌린지의 제목은 유니크해서 제목이 같은 것을 찾으면 된다.
		// 전체 챌린지 목록 가져오기
		List<Challenge> list = challengeService.selectChallengeAll();
		String targetTitle = info.getTitle();
		int targetNo = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().equals(targetTitle)) {
				targetNo = list.get(i).getNo();
				break;
			}
		}
		Challenge challenge = challengeService.selectChallengeByNo(targetNo);
		System.out.println("info.getNo() " + targetNo);

		// 팀만들기
		Team team1 = new Team("멋지당", 0, targetNo);
		Team team2 = new Team("예쁘당", 0, targetNo);

		int result2_1 = challengeService.insertTeam(team1);
		int result2_2 = challengeService.insertTeam(team2);

		// 방장 팀 추가 + 포인트 차감
		Users organizer2 = userService.selectById2(challenge.getOrganizer());
		challengeService.changePoint(organizer2.getId(), organizer2.getPoint() - info.getPoint());
		System.out.println("organizer2.getPoint() - info.getPoint() = " + (organizer2.getPoint() - info.getPoint()));

		// 방장이 1번 혹은 2번이냐에 따라 팀 배정
		// 방장 팀 가입 및 인원수 추가
		// 챌린지 번호로 팀 2개를 가져올 것 -> 방장은 1아님 2로 팀을 골라야한다.
		List<Team> teams = challengeService.team(targetNo);

		int organizerTeam = 0;
		if (challenge.getOrganizer_team() == 1)
			organizerTeam = teams.get(0).getNo();
		else
			organizerTeam = teams.get(1).getNo();

		challengeService.registTeam(info.getOrganizer(), challenge.getNo(), organizerTeam);
		challengeService.changeCount(organizerTeam, 1);

		// 생성된 챌린지 정보 반환
		return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);

	}

	// 2-2. 2개 팀정보 가져오기
	@GetMapping("/getTeams/{challenge_no}")
	public ResponseEntity<?> getTeams(@PathVariable int challenge_no) throws IOException {
		// 챌린지 번호로 팀 2개를 가져올 것
		List<Team> teams = challengeService.team(challenge_no);

		if (teams != null && teams.size() > 0)
			return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);

		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	// 3. 팀 가입하기 -> 프론트에서 미리 유저가 돈이 안되면 신청 못하게 해야함
	// 팀은 1아님 2로 선택됨 그래서 변환해줘야함
	//String users_id, int challenge_no, int team_no
	@PostMapping("/registTeam")
	public ResponseEntity<?> doRegistTeam(@RequestBody Users_has_challenge uhc) {
		// 챌린지 정보 가져오기
		Challenge challenge = challengeService.selectChallengeByNo(uhc.getChallenge_no());

		// 챌린지에 딸린 팀 2개 정보도 가져오기
		List<Team> teams = challengeService.team(uhc.getChallenge_no());
		System.out.println(uhc.getChallenge_no());
		
		// 참가자 정보 가져오기
		Users participator = userService.selectById2(uhc.getUsers_id());

		// 참가자가 원하는 팀 변환
		int real_team = 0;
		if (uhc.getTeam_no() == 1)
			real_team = teams.get(0).getNo();
		else
			real_team = teams.get(1).getNo();

		// 챌린지 참가 금액보다 참가자 금액이 더 적다면 참가할 수 없다.
		if (challenge.getPoint() > participator.getPoint())
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		// 참가할만한 금액이 있다면 ㄱ
		// 포인트 차감 후 팀 가입
		challengeService.changePoint(participator.getId(), participator.getPoint() - challenge.getPoint());

		int result = challengeService.registTeam(uhc.getUsers_id(), uhc.getChallenge_no(), real_team);

		// 팀정보 알아오기 인원수 늘려야하니까
		Team team = challengeService.selectTeam(real_team);
		challengeService.changeCount(real_team, team.getTeam_count() + 1);

		if (result > 0)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
	}

	// 4. 팀 취소하기 -> 포인트 회수 불가 -> 알럴트로 꼭 알리기
	@DeleteMapping("/deleteTeam")
	public ResponseEntity<?> doDeleteTeam(@RequestBody Users_has_challenge uhc) {

		// 취소 버튼을 누른 유저 정보 알아오기
		Users exitUser = userService.selectById2(uhc.getUsers_id());

		// 챌린저 방장 정보 알아오기
		Challenge challenge = challengeService.selectChallengeByNo(uhc.getChallenge_no());

		// team_no 는 1아님 2여서 실제 방넘버로 바꿔줘야함
		List<Team> teams = challengeService.team(uhc.getChallenge_no());
		int real_team = 0;
		if (uhc.getTeam_no() == 1)
			real_team = teams.get(0).getNo();
		else
			real_team = teams.get(1).getNo();

		// 방장이 팀 취소 불가!!
		if (exitUser.getId().equals(challenge.getOrganizer()))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		// 방장이 아니라면 팀 탈퇴
		int result = challengeService.deleteTeam(uhc.getUsers_id(), uhc.getChallenge_no(), real_team);
		if (result < 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);// 자기팀이 아닌데 나가려고 했음

		// 팀정보 알아오기 인원수 줄여야하니까
		Team team = challengeService.selectTeam(real_team);
		challengeService.changeCount(real_team, team.getTeam_count() - 1);

		if (result > 0)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
	}

	// 5. 챌린지 상태 변경
	//int no, int status
	@PostMapping("/changeStatus")
	public ResponseEntity<?> doChangeStatus(@RequestBody Challenge challenge) {

		// 만약 시작 상태로 변경해달라는 요청인데 인원수가 맞지 않으면 종료로 가게하기
		if (challenge.getStatus() == 1) {
			// 챌린지 2개팀 정보 가져오기
			List<Team> teams = challengeService.team(challenge.getNo());
			int count1 = teams.get(0).getTeam_count();
			int count2 = teams.get(1).getTeam_count();
			System.out.println(count1 + " " + count2);
			if (count1 <= 0 || count2 <= 0)
				challenge.setStatus(2);// 종료
		}

		int result = challengeService.changeStatus(challenge.getNo(), challenge.getStatus());// 바꿔줘

		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 6. 챌린지 수정하기 - 방장만 수정버튼이 보일 것임
	@PutMapping("/challengeUpdate")
	public ResponseEntity<?> doChallengeUpdate(@RequestBody Challenge info) throws IOException {

		// 파일 등록
//		org.springframework.core.io.Resource res = resLoader.getResource("resouces");

//		if (file != null && file.getSize() > 0) {
//			if (!res.getFile().exists())
//				res.getFile().mkdir();
//
//			String orgImg = file.getOriginalFilename();
//			info.setImage(System.currentTimeMillis() + "_" + orgImg);
//			file.transferTo(new File(res.getFile().getCanonicalPath() + "/" + info.getImage()));
//		}
		int result = challengeService.updateChallenge(info);

		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 7. 챌린지 삭제 - 대기중일 때만 -> 프론트에서 대기중일 때만 보내게 해야함
	@DeleteMapping("/challengeDelete/{no}")
	public ResponseEntity<?> doChallengeUpdate(@PathVariable int no) {
		int result = challengeService.deleteChallenge(no);

		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 8. 승리 여부 판단 챌린지 번호를 날려준다.
	@GetMapping("/winner/{challenge_no}")
	public ResponseEntity<?> doWinner(@PathVariable int challenge_no) {

		// 챌린지 정보를 가져온다.
		Challenge challenge = challengeService.selectChallengeByNo(challenge_no);

		// 진행중인 챌린지가 아니면 승자를 정할 수 없다 대기중 아님 종료니까
		if (challenge == null || challenge.getStatus() != 1)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		String[] start_date = challenge.getStart_date().split("-");
		String[] end_date = challenge.getEnd_date().split("-");

		long registPoint = challenge.getPoint();// 참가비

		// 2개팀 정보를 가져온다.
		List<Team> teams = challengeService.team(challenge_no);

		int team1_no = teams.get(0).getNo();
		int team2_no = teams.get(1).getNo();

		// 특정 챌린지 참가자들의 아이디와 팀 넘버를 가져온다.
		List<Users_has_challenge> info = challengeService.challengeListByno(challenge_no);

		int team1_score = 0;
		int team2_score = 0;
		List<String> team1_userID = new ArrayList<>();
		List<String> team2_userID = new ArrayList<>();

		for (int i = 0; i < info.size(); i++) {
			Users_has_challenge target = info.get(i);

			// 참가자가 참여한 팀 중 현재 챌린지의 팀 번호와 맞는 곳을 선택
			if (target.getTeam_no() == team1_no) {
				team1_userID.add(target.getUsers_id());
			} else {
				team2_userID.add(target.getUsers_id());
			}

			// 한 참가자씩 돌면서 일정 기간 내 운동 기록 몇 회 인지 확인 -> end_time 확인, 각 타임 별 소요 시간 확인해 30분 채워야
			// 1회 인정
			List<One_day_exercise> target_record = userService.selectExerciseById(target.getUsers_id());

			for (int j = 0; j < target_record.size(); j++) {
//				String[] user_start_date = target_record.get(j).getStart_time().split("-");
				String[] user_end_date = target_record.get(j).getEnd_time().split("-");
//				String[] user_time = target_record

				// 내가 운동을 끝마친 기간이 챌린지 시작~끝 기간 안에 들어오면 오케이
				if (Integer.parseInt(user_end_date[0]) >= Integer.parseInt(start_date[0])
						&& Integer.parseInt(user_end_date[1]) >= Integer.parseInt(start_date[1])
						&& Integer.parseInt(user_end_date[2].split(" ")[0]) >= Integer
								.parseInt(start_date[2].split(" ")[0])
						&& Integer.parseInt(user_end_date[0]) <= Integer.parseInt(end_date[0])
						&& Integer.parseInt(user_end_date[1]) <= Integer.parseInt(end_date[1])
						&& Integer.parseInt(user_end_date[2].split(" ")[0]) <= Integer
								.parseInt(end_date[2].split(" ")[0])) {

					// 유저의 해당 기록이 30분을 넘어야 인정
					// 내가 1팀 (멋지당 팀이다)
					if (target.getTeam_no() == team1_no) {
						// 시간 계산
						int timer = Integer.parseInt(target_record.get(j).getTotal_time().split(":")[0])
								+ Integer.parseInt(target_record.get(j).getTotal_time().split(":")[1])/60;

						if (timer >= 30)
							team1_score++;

					} else {
						// 내가 2팀(예쁘당 팀이다)
						int timer = Integer.parseInt(target_record.get(j).getTotal_time().split(":")[0])
								+ Integer.parseInt(target_record.get(j).getTotal_time().split(":")[1])/60;

						if (timer >= 30)
							team2_score++;
					}
				}
			}
		} // 점수 계산 끝

		System.out.println("최종점수 = " + team1_score + " " + team2_score);

		// 이긴팀한테 포인드 n빵하기
		
		if (team1_score > team2_score) {
			long prizePrice = registPoint * team2_userID.size();// 상금
			long divide = prizePrice / team1_userID.size();

			System.out.println("team2_userID.size() = " + team2_userID.size());

			for (int i = 0; i < team1_userID.size(); i++) {
				Users team1_user = userService.selectById2(team1_userID.get(i));
				challengeService.changePoint(team1_user.getId(), team1_user.getPoint() + divide+registPoint);
				System.out.println(team1_user.getId() + "님께 " + (team1_user.getPoint() + divide+registPoint) + "지급");
			}
			
			// 챌린지 종료
			doChangeStatus(new Challenge(challenge_no, 2));
			String result = "team1"+" "+divide;
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}

		else if (team1_score < team2_score) {
			long prizePrice = registPoint * team1_userID.size();// 상금
			long divide = prizePrice / team1_userID.size();

			System.out.println("divide2 = " + divide);

			for (int i = 0; i < team2_userID.size(); i++) {
				Users team2_user = userService.selectById2(team2_userID.get(i));
				challengeService.changePoint(team2_user.getId(), team2_user.getPoint() + divide +registPoint);
				System.out.println(team2_user.getId() + "님께 " + (team2_user.getPoint() + divide+registPoint) + "지급");
			}
			// 챌린지 종료
			doChangeStatus(new Challenge(challenge_no, 2));
			
			String result = "team2"+" "+divide;
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}

		else {
			
			//비겼기 때문에 참가비 그대로 가져감 -> registPoint
			//팀1
			for (int i = 0; i < team1_userID.size(); i++) {
				Users team1_user = userService.selectById2(team1_userID.get(i));
				challengeService.changePoint(team1_user.getId(), team1_user.getPoint() +registPoint);
				System.out.println(team1_user.getId() + "님께 " + (registPoint) + "지급");
			}
			
			//팀2
			for (int i = 0; i < team2_userID.size(); i++) {
				Users team2_user = userService.selectById2(team2_userID.get(i));
				challengeService.changePoint(team2_user.getId(), team2_user.getPoint() +registPoint);
				System.out.println(team2_user.getId() + "님께 " + (registPoint) + "지급");
			}
			
			
			// 챌린지 종료
			doChangeStatus(new Challenge(challenge_no, 2));
			// 비김
			String result = "draw"+" "+registPoint;
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}

	}

	// 9. 특정 챌린지의 참가자들 데려오기
	@GetMapping("/teammember/{challenge_no}")
	public ResponseEntity<?> getMember(@PathVariable int challenge_no) {

		// 특정 챌린지 참가자들의 아이디와 팀 넘버를 가져온다.
		List<Users_has_challenge> info = challengeService.challengeListByno(challenge_no);// 유저아이디

		// 유저아이디로 특정 유저 정보 전부 받아오기
		List<Users>[] teamInfo = new ArrayList[2];
		for (int i = 0; i < 2; i++) {
			teamInfo[i] = new ArrayList<>();
		}

		for (int i = 0; i < info.size(); i++) {
			// 멋지당팀
			if (info.get(i).getTeam_no() % 2 == 1)
				teamInfo[0].add(userService.selectById2(info.get(i).getUsers_id()));

			else
				teamInfo[1].add(userService.selectById2(info.get(i).getUsers_id()));
		}

		if (teamInfo != null && teamInfo.length > 0)
			return new ResponseEntity<List<Users>[]>(teamInfo, HttpStatus.OK);
		else
			return new ResponseEntity<List<Users>[]>(teamInfo, HttpStatus.NO_CONTENT);

	}
	
	//10. 특정 유저의 챌린지 보내기
	@GetMapping("/member/{users_id}")
	public ResponseEntity<?> getMember(@PathVariable String users_id) {

		// 특정 챌린지 참가자들의 아이디와 팀 넘버를 가져온다.
		List<Challenge> info = challengeService.myChallenge(users_id);// 유저아이디
		System.out.println(info);


		if (info != null && info.size() > 0)
			return new ResponseEntity<List<Challenge>>(info, HttpStatus.OK);
		else
			return new ResponseEntity<List<Challenge>>(info, HttpStatus.NO_CONTENT);

	}
	
	//특정 챌린지 가져오기
	@GetMapping("/getChallenge/{no}")
	public ResponseEntity<?> getChallenge(@PathVariable int no) {

		// 특정 챌린지 참가자들의 아이디와 팀 넘버를 가져온다.
		Challenge challenge = challengeService.selectChallengeByNo(no);


		if (challenge != null)
			return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	

}
