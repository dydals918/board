program : 스프링 부트 기반 커뮤니티 게시판
tool : intelli J
version : spring boot - 3.1.4
          JDK - 17
database : MySQL
dependency : thymeleaf, spring web, spring security, spring JPA, MySQL, p6spy, lombok

설계
Entity : Member - 사용자
         Post - 게시글
         Comment - 댓글

Service : Member - 멤버 생성, 로그인, ID로 회원찾기, 방문 횟수 조회
          Post - 모든 게시글 조회, 게시글 ID로 조회, 글쓰기, 글삭제, 게시글 좋아요-싫어요 기능, 게시글 방문횟수 업데이트, 게시글 검색
          Comment - 댓글 작성, 댓글 작성 횟수 조회

template : index page(home page), login page, member register page, mypage, board page, board view page, board update page, board write page
