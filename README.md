# 소코반 게임
<img src="https://github.com/typoscript/sokoban/blob/main/images/sokoban-exe-icon.png" width="200" height="200" />

<br>

## 목차
> ## [게임 실행 방법](#how_to_run) <br>
> ## [플레이 모드](#mode_play) <br>
> ## [맵 제작 모드](#mode_map_maker) <br>
> ## [Repository 폴더 구조](#repo_structure) <br>
> ## [프로젝트에 쓰인 툴](#tools)

<br>

# 모드
# <a name="how_to_run"></a> 게임 실행 방법

## 본 저장소를 ZIP 파일로 다운 받아 실행하기
1. 저장소 화면에서 초록색 **Code** 버튼 클릭.
2. **Download ZIP** 클릭하여 저장소를 다운 받기.
3. 다운 받은 **ZIP** 파일을 압축 풀기.
4. 압축 풀은 폴더 속, **game** 폴더 속, **jdk** 폴더 진입.
5. **lib** 폴더 두 개 존재 확인 (압축된 **lib** 폴더는 **ZIP** 형태로 다운 받은 사용자를 위한 폴더).
6. **lib** 폴더 삭제 후 압축된 **lib** 폴더 압축 풀기.
7. **game** 폴더로 돌아가서 **sokoban.exe** 파일 실행하여 게임 플레이.

<br>

## 본 저장소를 Git Clone 명령어로 다운 받아 실행하기
1. 터미널에 **git clone** 명령어로 본 저장소를 **clone** 하기.
2. **clone**한 **sokoban** 폴더 속 **game** 폴더 속 **sokoban.exe** 파일 실행하여 게임 플레이.

<br>

### lib 폴더 두 개 존재 이유
- **jdk** 폴더 속 **lib** 폴더 속 **modules**이라는 파일 사이즈가 **100MB** 초과 (**git push** 시, 파일 사이즈가 **100MB** 이상일 시, 업로드 불가).
- 그리하여 **git lfs**를 활용하여 파일 사이즈가 큰 **modules**를 업로드 가능하게 하지만, **Github**에서 저장소를 **Download ZIP** 옵션으로 다운 받을 시, **git lfs**를 활용하여 업로드한 **modules** 파일이 정상적이지 않음 (**git clone** 사용 시에만, 정상적인 파일 다운 가능).
- 해결 방안으로 **modules** 파일이 속한 **lib** 폴더를 압축 (압축 시, 사이즈가 **100MB** 이하), 결과적으로 저장소를 **Download ZIP** 옵션으로 받아도 온전한 폴더 다운 가능.
- 결론: **jdk** 속 **lib** 폴더는 **git clone 사용자용** 그리고 압축된 **lib** 폴더는 **저장소를 ZIP 형태로 다운 받는 사용자용**.

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
- 게임 실행 파일 폴더 (폴더 속 폴더 & 파일 리스트: exe 파일, 게임 맵 데이터 폴더, exe 파일 실행을 위한 jdk 폴더).

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
