#include <ESP8266WiFi.h>
#include "PubSubClient.h"
#include <DHT11.h>
char ssid[]="brainup cafe"; //Wi-Fi NAME
char password[]="09091010"; //Wi-Fi Password
byte server1[]={192,168,0,115}; //MQTT 서버 IP( 내 컴퓨터 현재 ip주소)
const char* clientName="1004Client";
int port=1883; //MQTT PORT는 1883번
const int Pin_soil=A0;
DHT11 dht11(4);
WiFiClient client;

void msgReceived(char *topic,byte *payload,unsigned int uLen){  //callback 함수

  
}
PubSubClient mqttClient(server1,port,msgReceived,client);

void setup() {
  Serial.begin(115200);
  delay(10);
  Serial.println();
  Serial.println();
  Serial.println("Connecting...");
  Serial.println(ssid);
  WiFi.begin(ssid,password);
  while(WiFi.status()!=WL_CONNECTED)
  {
    delay(500);
    Serial.println("연결중...");
  }
  Serial.println();
  Serial.println("Wi-Fi Connected!");
  Serial.println(WiFi.localIP()); //
  //MQTT 서버에 접속
  if(mqttClient.connect("Arduino")){
    Serial.println("MQTT Broker Connected..!");
    mqttClient.subscribe("led"); //데이터를 읽어갈 구독자를 등록
  }
 

}

void loop() {
  mqttClient.loop();
  int nSoil=analogRead(Pin_soil);
  int nSoilper=map(nSoil,170,1023,100,0);
  float tmp,hum;
  int err=dht11.read(hum,tmp); //dht로 데이터 읽어들이고
  if(err==0) //정상적으로 읽으면 0이 넘어옴.
  {
   char message[64]="",pTmpBuf[50],pHumBuf[50]; //JSON으로 변환해주기 위한 버퍼
   dtostrf(tmp,5,2,pTmpBuf);       //5자리를 잡고 소수점 2자리까지 JSON으로 변경
   dtostrf(hum,5,2,pHumBuf);       //5자리를 잡고 소수점 2자리까지 JSON으로 변경
   sprintf(message,"{\"tmp\":%s,\"hum\":%s}",pTmpBuf,pHumBuf);
   mqttClient.publish("dht11",message);
  }
  if(nSoil>0){
    char mess[64]="",pSoilhum[50];
    dtostrf(nSoilper,5,2,pSoilhum);
    sprintf(mess,"{\"soilhum\":%s}",pSoilhum);
    mqttClient.publish("yl38",mess);
  }
  delay(3000); //3초

}
