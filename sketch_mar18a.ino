#include <SPI.h>
#include <MFRC522.h>
#define SS_PIN 4
#define RST_PIN 5
MFRC522 rfid(SS_PIN, RST_PIN); // Instance of the class
MFRC522::MIFARE_Key key; 
byte nuidPICC[4];
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <String.h>
ESP8266WiFiMulti WiFiMulti;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
#include <LiquidCrystal_I2C.h>
#include <Wire.h>
LiquidCrystal_I2C lcd(0x3F, 16, 2);



void setup() { 
  Serial.begin(115200);
  SPI.begin(); // Init SPI bus
  rfid.PCD_Init(); // Init MFRC522 
    Wire.begin(0, 2);
   lcd.init(); // Iniciamos el LCD
    lcd.backlight(); // Prendemos la Iluminacion del LCD
    lcd.clear();
    lcd.print("  Hello World!!  ");

  for (byte i = 0; i < 6; i++) {
    key.keyByte[i] = 0xFF;
  }

  Serial.println(F("This code scan the MIFARE Classsic NUID."));
  Serial.print(F("Using the following key:"));
  printHex(key.keyByte, MFRC522::MF_KEY_SIZE);
  for (uint8_t t = 4; t > 0; t--) {
    Serial.printf("[SETUP] WAIT %d...\n", t);
    Serial.flush();
    delay(1000);
  }
  WiFi.mode(WIFI_STA);
  WiFiMulti.addAP("MIT3", "12345678c"); // 와이파이접속
  pinMode(16, OUTPUT); 
}
 
void loop() {
  lcd.clear();
  if ( ! rfid.PICC_IsNewCardPresent())
    return;

  if ( ! rfid.PICC_ReadCardSerial())
    return;

  Serial.print(F("PICC type: "));
  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
  Serial.println(rfid.PICC_GetTypeName(piccType));

  if (piccType != MFRC522::PICC_TYPE_MIFARE_MINI &&  
    piccType != MFRC522::PICC_TYPE_MIFARE_1K &&
    piccType != MFRC522::PICC_TYPE_MIFARE_4K) {
    Serial.println(F("Your tag is not of type MIFARE Classic."));
    return;
  }
  //연다라 등록 불가 코드
  if (rfid.uid.uidByte[0] != nuidPICC[0] || 
    rfid.uid.uidByte[1] != nuidPICC[1] || 
    rfid.uid.uidByte[2] != nuidPICC[2] || 
    rfid.uid.uidByte[3] != nuidPICC[3] ) {
    Serial.println(F("A new card has been detected."));
    for (byte i = 0; i < 4; i++) {
      nuidPICC[i] = rfid.uid.uidByte[i];
    }
    Serial.println(F("The NUID tag is:"));
    Serial.print(F("In hex: "));
    printHex(rfid.uid.uidByte, rfid.uid.size);
    Serial.println();
    Serial.print(F("In dec: "));
    printDec(rfid.uid.uidByte, rfid.uid.size);
    Serial.println();
  }
  else Serial.println(F("Card read previously."));
  rfid.PICC_HaltA();
  rfid.PCD_StopCrypto1();
  
        String nfcid[4];
    for(int i=0; i<4; i++){
      nfcid[i]=String(rfid.uid.uidByte[i],HEX);
      if(rfid.uid.uidByte[i]<10){
        nfcid[i]="0"+nfcid[i];
      }
    }
    String nfc_id=nfcid[0]+nfcid[1]+nfcid[2]+nfcid[3];
    Serial.print(nfc_id);
    String URL = "http://192.168.3.18:8090/check?nfcid="+nfc_id;
    Serial.print(URL);
    if ((WiFiMulti.run() == WL_CONNECTED)) {
      
    WiFiClient client;
    HTTPClient http;
    
    Serial.print("[HTTP] begin...\n");
    if (http.begin(client, URL)) {  // HTTP
      Serial.print("[HTTP] GET...\n");
      // start connection and send HTTP header
      int httpCode = http.GET();
      // httpCode will be negative on error
      if (httpCode > 0) {
        // HTTP header has been send and Server response header has been handled
        Serial.printf("[HTTP] GET... code: %d\n", httpCode);
        // file found at server
        if (httpCode == HTTP_CODE_OK || httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
          String payload = http.getString(); 
          int a = payload.length();
          Serial.println(a);
                  if(a < 30){
                  lcd.setCursor(0,0);
                  lcd.print("  "+nfcid[0]+" "+nfcid[1]+" "+nfcid[2]+" "+nfcid[3]+"  ");
                  lcd.setCursor(0,1);
                  lcd.print("! Not Execute !");
                  //led전구
                   for(int i = 0; i < 5; i++){
                             digitalWrite(16, HIGH);
                             delay(100);
                             digitalWrite(16, LOW); 
                             delay(100);
                             
                    }     
                   delay(2000);
                  } else if(30< a && a < 1000){
                  //lcd 1602 출력
                  lcd.setCursor(0,0);
                  lcd.print("  "+nfcid[0]+" "+nfcid[1]+" "+nfcid[2]+" "+nfcid[3]+"  ");
                  lcd.setCursor(0,1);
                  lcd.print("Happy  Commute!!");
                   for(int i = 0; i < 5; i++){
                             digitalWrite(16, HIGH);
                             delay(100);
                             digitalWrite(16, LOW); 
                             delay(100);
                    }
                   delay(2000);
                    }else{
                         //lcd 1602 출력
                      lcd.setCursor(0,0);
                      lcd.print("  "+nfcid[0]+" "+nfcid[1]+" "+nfcid[2]+" "+nfcid[3]+"  ");
                      lcd.setCursor(0,1);
                      lcd.print("See you tomorrow");
              
                      //led전구
                       for(int i = 0; i < 5; i++){
                                 digitalWrite(16, HIGH);
                                 delay(100);
                                 digitalWrite(16, LOW); 
                                 delay(100);
                        }
                        delay(2000);
                  }
                  Serial.println(payload);

          }
      } else {
        Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
      }
      http.end();
    } else {
      Serial.printf("[HTTP} Unable to connect\n");
    }
  }
}
void printHex(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], HEX);
  }
}
void printDec(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], DEC);
  }
}
