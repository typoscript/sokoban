# 소코반 게임
<img src="https://github.com/typoscript/sokoban/blob/main/images/sokoban-exe-icon.png" width="200" height="200" />

<br>

## 목차
> ## [플레이 모드](#mode_play) <br>
> ## [맵 제작 모드](#mode_map_maker) <br>
> ## [Repository 폴더 구조](#repo_structure) <br>
> ## [프로젝트에 쓰인 툴](#tools)

<br>

# 게임 플레이 미리보기
<img src="https://github.com/typoscript/sokoban/blob/main/images/gameplay.gif" width="500" height="500" />

<br>

## 게임 설명
> **게임 플레이 모드**
  - 플레이어를 움직여 모든 상자를 창고(동그라미 빨간색 물체) 위치에 놓으면 스테이지 클리어.
> **게임 맵 제작 모드**
  - 게임 맵 추가/삭제/편집.

<br>

# 모드
## <a name="mode_play"></a> 플레이 모드
<img src="https://github.com/typoscript/sokoban/blob/main/images/game_preview.PNG" width="500" height="500" />

### 설명
> 기본 10개 맵으로 레벨 1부터 10까지의 스테이지 플레이 가능.
>
> 마지막 스테이지 클리어 시, 게임 승리.

### 조작법
- 이동 방법: **방향키**.
- 현재 스테이지 재시작 방법: **스페이스바**.
- 로비로 이동 방법 (주의, 로비로 이동 시, 게임 스테이지 초기화): 상단 왼쪽 **뒤로가기** 버튼 클릭.

<br>

## <a name="mode_map_maker"></a> 맵 제작 모드
<img src="https://github.com/typoscript/sokoban/blob/main/images/game_preview_map_maker_mode.PNG" width="500" height="500" />

### 설명
> 맵 추가/수정/삭제 가능.
>
> 방향키를 이용하여 플레이어 이동과 구조물 제거 가능.

<br>

### 조작법
- 이동 방법: **방향키**.
- 설치한 구조물 제거 방법: 플레이어를 제거할 구조물로 이동.

<br>

### 벽/박스/창고 설치 키
- **w** - 플레이어 위치에 벽 설치
- **b** - 플레이어 위치에 박스 설치
- **h** - 플레이어 위치에 창고 설치

<br>

### 맵 제작 시 주의사항
- 맵 저장 시, 박스의 갯수와 창고 갯수가 일치해야 함.
- 맵 저장 시, 플레이어가 구조물과 동일한 위치에 존재할 경우 맵 저장 불가 (구조물 설치 후 바로 저장 불가).
- 맵 저장 시, 현재 플레이어의 위치가 게임 플레이 시, 플레이어의 초기 위치.
- 맵 승리 원리는 현재 맵 클리어 후, 현재 레벨에서 1을 더한 레벨에 맞는 맵이 없을 시, 게임 승리 처리.
- 맵 파일 저장 위치: **game** 폴더 속 **maps** 폴더 속 위치.

<br>

## <a name="repo_structure"></a> Repository 폴더 구조
> **game**
- 게임 실행 파일 폴더 (본 폴더를 다운 후 sokoban.exe 파일을 실행하여 게임 플레이 가능).

> **images**
- Github README.md 파일에 사용된 이미지와 sokoban.exe 아이콘 이미지 보관 폴더.

> **sokoban**
  - 프로젝트 소스코드 폴더.

<br>

## <a name="tools"></a> 프로젝트에 쓰인 툴
- **launch4j**
  - (프로젝트 EXE 파일로 변환 https://launch4j.sourceforge.net)
- **ezgif**
  - (게임 플레이 영상 gif로 변환 사이트 https://ezgif.com/video-to-gif)
- **piskelapp**
  - (게임 픽셀 이미지 제작 사이트 https://www.piskelapp.com)

<br>

## 개발 기간
2023-02-23 ~ 2023.04-24
