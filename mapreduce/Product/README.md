### 0. 시작 전
- VMWare에 있는 Intellij를 이용한 코드입니다.
- 로컬에서 개발하실 분들은 jar파일을 만든 후, VMWare에서 jar 파일 실행 코드만 돌려주시면 됩니다.


### 1. Setting

- VMWare 한글 설정

[[Ubuntu 20.04]한글 입력기 설치 & 설정하기](https://velog.io/@yujo/Ubuntu-20.04%ED%95%9C%EA%B8%80-%EC%9E%85%EB%A0%A5%EA%B8%B0-%EC%84%A4%EC%B9%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)

```bash
$ cd /home/hadoop
$ source .bashrc // Path를 지정하기 위한 명령어
$ start-dfs.sh // dfs 데몬 실행

# stop-dfs.sh // dfs 데몬 중지

$ jps // 수행중인 java 프로세스 리스트를 확인한다
	- DataNode
	- SecondaryNameNode
	- NameNode
	- Jps
```

```bash
$ hdfs dfs -mkdir {카테고리명}_product
$ hdfs dfs -mkdir {카테고리명}_review

$ hdfs dfs -ls # product, review 디렉토리가 있어야 함
```

### 2. 데이터를 HDFS에 넣기

```bash
- VMWare상에 데이터 파일을 복사해둔 후 다음 명령어 실행

$ hdfs dfs -put airfryer_detail.txt {카테고리명}_product # 상품 데이터
$ hdfs dfs -put Airfryer.txt {카테고리명}_review # 리뷰 데이터
```

### 3. JAR 파일 생성

[[IntelliJ] JAR 파일 생성하기](https://ifuwanna.tistory.com/244)

### 4. MapReduce 코드 실행

```bash
$ cd /home/hadoop/Project
$ hadoop jar Product.jar {카테고리명}_product

# hadoop jar [jar file] [program name] <input arguments...>
```
