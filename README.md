# Tarot-about-U(backend)

Tarot Project 백엔드코드입니다

프론트엔드코드 보러가기 →
[Frontend_code](https://github.com/tjddus528/TaroAboutU_front)

### 프로젝트
MZ세대의 고민해소 혹은 재미로 보는 타로 기능과
관련된 고민이나 하루의 생각을 정리할 수 있는 일기장 기능을 함께한
타로 다이어리 어플

### 사용자 Flow
![image](https://user-images.githubusercontent.com/73892693/174945248-111d80e7-696e-4bb8-9143-6abe4bd83c9e.png)

### API sheet
| Index | entity | Method | URI | Description | 테스트  | 서버 반영 | 프론트 api 연동 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1.1 | users | GET | /users/list | 전체 유저 조회 API | O | O | O |
| 2.1 | cards | GET | /cards/tarot/:tarotId | 특정 타로1장 조회 API | O | O | O |
| 2.2 |  | GET | /cards/set/:setId | 특정 타로3장 조회 API | O | O | O |
| 3.1 | inventory | POST | /inventory/result/:userId | 보관함 타로결과 추가 API | O | O | O |
| 3.2 |  | GET | /inventory?userId= | 보관함 전체 조회 API | O | O | O |
| 3.3 |  | GET | /inventory/tarot/:userId?questionId=?date= | 타로결과 리스트 조회 API | O | O | O |
| 3.4 |  | GET | /inventory/set/:userId?date= | 세트결과 리스트 조회 API | O | O | O |
| 4.1 | diary | POST | /diary | 다이어리 생성 API | O | O | O |
| 4.2 |  | GET | /diary | 다이어리 조회 API | O | O | O |
| 4.3 |  | PATCH | /diary/:diaryId= | 다이어리 수정 API | O | O | O |
| 4.4 |  | PATCH | /diary/:diaryId/status | 다이어리 삭제 API | O | O | O |
