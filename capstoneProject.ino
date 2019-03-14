// Gaming Mouse Combo- EE416 Capstone Project by mlk5436
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <Keyboard.h>


#define OLED_RESET 4
Adafruit_SSD1306 display(OLED_RESET);

#define NUMFLAKES 10
#define XPOS 0
#define YPOS 1
#define DELTAY 2


#define LOGO16_GLCD_HEIGHT 16 
#define LOGO16_GLCD_WIDTH  16 
#define MAXLENOFNAME 10
#define MAXLENOFKEY 20

#if (SSD1306_LCDHEIGHT != 32)
#error("Height incorrect, please fix Adafruit_SSD1306.h!");
#endif

// Rotary encoder declarations
const int pinA = 4; // Our first hardware interrupt pin is digital pin 2
const int pinB = 5; // Our second hardware interrupt pin is digital pin 3

int encoderPos = 0; //this variable stores our current value of encoder position. Change to int or uin16_t instead of byte if you want to record a larger range than 0-255
int oldEncPos = 0; //stores the last encoder position value so we can compare to the current reading and see if it has changed (so we know when to print to the serial monitor)
int counter = 0;
int aState;
int aLastState;
char *combos[] = {"  COMBO A", "  COMBO A", "  COMBO B", "  COMBO B", "  COMBO C", "  COMBO C"};
int comboNum = 0;
// Button reading, including debounce without delay function declarations
const byte buttonPin = 6; // this is the Arduino pin we are connecting the push button to
byte oldButtonState = HIGH;  // assume switch open because of pull-up resistor
const unsigned long debounceTime = 10;  // milliseconds
unsigned long buttonPressTime;  // when the switch last changed state
boolean buttonPressed = 0; // a flag variable


struct combo{
  int comboNum;
   char comboName[MAXLENOFNAME];
   char keys[MAXLENOFKEY];
   int intervals[MAXLENOFKEY];
  };
struct combo *pointer[10];
int comboAmount;

void setup() {
  //Rotary encoder section of setup
  pinMode(pinA, INPUT); // set pinA as an input, pulled HIGH to the logic voltage (5V or 3.3V for most cases)
  pinMode(pinB, INPUT); // set pinB as an input, pulled HIGH to the logic voltage (5V or 3.3V for most cases)
  // button section of setup
  pinMode (buttonPin, INPUT_PULLUP); // setup the button pin
  // DEBUGGING section of setup
  
  Keyboard.begin();

  Serial.begin(9600);     // DEBUGGING: opens serial port, sets data rate to 9600 bps
  Serial.setTimeout(50);
  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);  // initialize with the I2C addr 0x3C (for the 128x32)
  // init done
  
 
   
  aLastState = digitalRead(pinA);
  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);  // initialize with the I2C addr 0x3C (for the 128x32)
  // init done
  
   // Clear the buffer.
  display.clearDisplay();
  
  // Show image buffer on the display hardware.
  // Since the buffer is intialized with an Adafruit splashscreen
  // internally, this will display the splashscreen.
  display.display();
  delay(2000);
 


  // text display tests
  display.setTextSize(2);
  display.setTextColor(WHITE);
  display.setCursor(0,0);
  display.println("GCM V.1.0");
  display.println("----------");
  display.display();
  delay(2000);
  display.clearDisplay();

  // invert the display
  display.invertDisplay(true);
  delay(1000); 
  display.invertDisplay(false);
  delay(1000); 
  display.clearDisplay();
   setCombos();


}

void loop() {
  /*
  
   String text = Serial.readString();
  
  //String text = "Combo A,x,100,z,100,w";
 
  if(text.length() != 0){
     int size = text.length();
      char *buffer = calloc(size,1);
      text.toCharArray(buffer,size);
      setCombos(buffer); 
    }
*/
    rotaryMenu();
}

void setCombos(){

  struct combo A;
  strcpy(A.comboName, "  COMBO A");
  strcpy(A.keys, "qwe");
  for(int i = 0; i < strlen(A.keys);i++){
    A.intervals[i] = 100;
    }
  pointer[comboAmount] = &A;
  comboAmount++;
  
  struct combo B;
  strcpy(B.comboName, "  COMBO B");
  strcpy(B.keys, "wew");
  for(int i = 0; i < strlen(B.keys);i++){
    B.intervals[i] = 100;
    }
 pointer[comboAmount] = &B;
 comboAmount++;
  struct combo C;
  strcpy(C.comboName, "  COMBO C");
  strcpy(C.keys, "wee");
  for(int i = 0; i < strlen(C.keys);i++){
    C.intervals[i] = 100;
    }
 pointer[comboAmount] = &C;
 comboAmount++;

 }
 

void rotaryMenu() { //This handles the bulk of the menu functions without needing to install/include/compile a menu library
  // Button reading with non-delay() debounce - thank you Nick Gammon!
  aState = digitalRead(pinA);
    oldEncPos = encoderPos;
  if(aState != aLastState){
    if(digitalRead(pinB) != aState){
      counter++;
    }else{
      counter--;
    }
    //Serial.println (counter); // DEBUGGING: print that button has been closed
     display.clearDisplay();
        display.setTextSize(2);
        display.setTextColor(WHITE);
        display.setCursor(0,1.5);
        comboNum = counter%6;
        if (comboNum < 0)
          comboNum = comboNum*-1;
        
       Serial.println (counter); // DEBUGGING: print that button has been closed

        display.println(combos[comboNum]);
        display.display();
          
    }

  byte buttonState = digitalRead (buttonPin);
  if (buttonState != oldButtonState){
    if (millis () - buttonPressTime >= debounceTime){ // debounce
      buttonPressTime = millis ();  // when we closed the switch 
      oldButtonState =  buttonState;  // remember for next time 
      if (buttonState == LOW){
        buttonPressed = 1;
          if(strcmp(combos[comboNum],"  COMBO A")==0){
            for(int i = 0; i < strlen(pointer[0]->keys); i++){
               Keyboard.println(pointer[0]->keys[i]);
               delay(100);
              //delay(pointer[0]->intervals[i]);
              }
          }

          if(strcmp(combos[comboNum],"  COMBO B")==0){
            for(int i = 0; i < strlen(pointer[1]->keys); i++){
               Keyboard.println(pointer[1]->keys[i]);
                delay(100);
              //delay(pointer[1]->intervals[i]);
              }
          }
          
        if(strcmp(combos[comboNum],"  COMBO C")==0){
            for(int i = 0; i < strlen(pointer[2]->keys); i++){
               Keyboard.println(pointer[2]->keys[i]);
               delay(100);

              //delay(pointer[2].intervals[i]);
              }
          }
          
         
          /*
       if(strcmp(combos[comboNum],"  COMBO B")==0){
           Keyboard.println("q");
          delay(100);
          Keyboard.println("q");
          delay(100);
          Keyboard.println("e");
          }
        if(strcmp(combos[comboNum],"  COMBO C")==0){
           Keyboard.println("w");
          delay(100);
          Keyboard.println("e");
          delay(100);
          Keyboard.println("q");
          }
          */
      }
      else {
        buttonPressed = 0;  
      }  
    }  // end if debounce time up
  } // end of state change
 
  
} 

