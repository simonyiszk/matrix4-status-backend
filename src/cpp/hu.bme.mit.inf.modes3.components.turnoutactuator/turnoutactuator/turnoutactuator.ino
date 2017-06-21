typedef enum TurnoutState_ {
  STRAIGHT,
  DIVERGENT
} State;

// Hardware specific constants
const int pwmPin = 3;
const int actStrPin = 7;
const int actDivPin = 8;
const int senseStrPin = 9;
const int senseDivPin = 10;

// Application specific values for Servo control PWM
const int straightDuty = 50;
const int divergentDuty = 78;

State currentState;

void changeState(State state){
  digitalWrite(senseStrPin, state == STRAIGHT);
  digitalWrite(senseDivPin, state == DIVERGENT);
  analogWrite(pwmPin, state == STRAIGHT ? straightDuty : divergentDuty);
  currentState = state;
}

void setup() {
  // Enabling UART for debug and traceability
  Serial.begin(115200);

  Serial.print("Starting...");

  // Setting pin modes
  pinMode(pwmPin, OUTPUT);
  pinMode(senseStrPin, OUTPUT);
  pinMode(senseDivPin, OUTPUT);

  pinMode(actStrPin, INPUT);
  pinMode(actDivPin, INPUT);

  // Setting the initial state of the turnout
  changeState(STRAIGHT);
  
  Serial.print("Setup done.");
}

void loop() {
  State newState;
  int isActStr = digitalRead(actStrPin);
  int isActDiv = digitalRead(actDivPin);

  // If the control signals are the same, do nothing
  if(! (isActStr ^ isActDiv) ) {
    return;
  }

  newState = isActStr ? STRAIGHT : DIVERGENT;

  // If there was no change in the state, do nothing
  if(newState == currentState) {
    return;
  }

  // Need to change state
  
  Serial.print("Changing state from:");
  Serial.print(currentState);
  Serial.print("Changing state to:");
  Serial.print(newState);
  changeState(newState);
  
}