
# Plant Helpi

#### 프로젝트 설명
- 키우던 5개의 화분을 관리를 제대로 하지 못해 모두 시들어버렸고 식물의 토양상태, 현재 주변환경이 식물에게 적합한가를 알 수 있는 방법이 있으면 좋겠다는 생각을 하였고 
  생각한 내용을 개발에 옮긴 프로젝트입니다.
  
#### 프로젝트 개요
  ![1](https://user-images.githubusercontent.com/66250847/98648197-4aed1b00-2379-11eb-99a4-dbb905e68f2e.png)
  
#### 동작방식

- 1. wemos 보드와 필요한 센서(온습도센서,토양습도)를 통해 원하는 값을 측정한다.
- 2. MQTT 프로토콜을 이용하여 NODE서버와 통신한다-> 측정한 데이터를 전송.
- 3. Cloud mongoDB에 해당데이터를 저장한다.
- 4. volley 라이브러리를 통해 안드로이드 애플리케이션과 node서버에게 request를 전송하고 mongoDB에 저장된 최근날짜순 10개 데이터를 받아온다.
- 5. Spinner를 통해 2가지 센서에 대한 정보를 각각 보여주고 저장된 식물 데이터베이스에 기초하여 사용자에게 알림서비스를 준다.

--------------------------------------------------------------------------------------------------------------------------------------------

#### 애플리케이션 구현 모습

 ![2](https://user-images.githubusercontent.com/66250847/98647847-d31ef080-2378-11eb-8d66-1be1e8c159ab.png)
 ![3](https://user-images.githubusercontent.com/66250847/98647867-dd40ef00-2378-11eb-82aa-bdf99ab1ceb2.png)
 
 -------------------------------------------------------------------------------------------------------------------------------------------
 #### 상황별 토양습도 체크
 ![4](https://user-images.githubusercontent.com/66250847/98647891-e5992a00-2378-11eb-9848-c08bd4d33103.png)
 
 -------------------------------------------------------------------------------------------------------------------------------------------
 
 #### 최종결과
 
 ![5](https://user-images.githubusercontent.com/66250847/98647927-edf16500-2378-11eb-8e42-c5be030a8d10.png)
 
  
  

  
  
  
