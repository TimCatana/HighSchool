/*==============================================================================
    Project: RAINBO-Starter
    Version: 1.0				Date: March 9, 2018
    Target: RAINBO				Processor: PIC12F1840

 RAINBO NeoPixel basic driver code to set all NeoPixels to the same colour.
 =============================================================================*/

#include    "xc.h"              // XC compiler general include file

#include    "stdint.h"          // Include integer definitions
#include    "stdbool.h"         // Include Boolean (true/false) definitions

#include	"RAINBO.h"

#include     "stdio.h"
#include     "stdlib.h"
// Include user-created constants and functions

// Byte variables for NeoPixel functions

unsigned char red = 5; // RGB colour bytes and default starting colour
unsigned char green = 5;
unsigned char blue = 5;

unsigned char colour = 5;
unsigned char LED = 3;
unsigned char increase = 5;
unsigned char speed = 0;
unsigned char maxbrightness = 255;

unsigned char redArray[8]; // RGB colour bytes and default starting colour
unsigned char greenArray[8];
unsigned char blueArray[8];
unsigned char arrayIndex = 0;

bool isincreasing = true;
bool isreallybright = false;
bool isred = true;

const char maxLEDs = 30; // Number of LEDs in the string

unsigned char temp; // Temporary byte storage
unsigned char i; // Generic index counter

unsigned char adConvert(unsigned char chan) {
    ADON = 1; // Turn A-D converter on
    ADCON0 = (ADCON0 & 0b10000011); // Change the conversion channel by wiping
    ADCON0 = (ADCON0 | chan); // CHS bits and ORing with selected channel
    __delay_us(2); // Give input time to settle
    GO = 1; // Start the conversion

    while (GO); // Wait for the conversion to finish (GO=0)

    ADON = 0; // Turn A-D converter off and
    return (ADRESH); // return the upper 8-bits (MSB) of the result
}

/*==============================================================================
 *	neoRGB - sets requested number of Neopixel LEDs to same RGB value.
 =============================================================================*/

//MY MODIFIED NEORGB FUNCTION THAT I USE TO RUN MY "FIREFLY" CODE. IT RUNS A SINGLE LED AT A TIME

void neoRGB(unsigned char red, unsigned char green, unsigned char blue, unsigned char leds, unsigned char singleled) {
    for (leds; leds != 0; leds--) // Repeat all 24 colour bits for each LED
    {
        if (leds == singleled) {
            temp = green; // Copy green byte, prepare to shift MSB first
            for (i = 8; i != 0; i--) // PWM each bit in assembly code for speed
            {
                asm("bsf LATA,4"); // Make N2 output high
                asm("nop"); // Wait one instruction cycle
                asm("btfss _temp,7"); // If MSB == 1, skip next instruction
                asm("bcf LATA,4"); // Make N2 output low (0)
                asm("lslf _temp,f"); // Shift next bit into MSB position
                asm("bcf LATA,4"); // Make N2 output low (1)
            }
            temp = red; // Red next, same as green.
            for (i = 8; i != 0; i--) {
                asm("bsf LATA,4");
                asm("nop");
                asm("btfss _temp,7");
                asm("bcf LATA,4");
                asm("lslf _temp,f");
                asm("bcf LATA,4");
            }
            temp = blue; // Blue last. 
            for (i = 8; i != 0; i--) {
                asm("bsf LATA,4");
                asm("nop");
                asm("btfss _temp,7");
                asm("bcf LATA,4");
                asm("lslf _temp,f");
                asm("bcf LATA,4");
            }
        } else {
            temp = 0; // Copy green byte, prepare to shift MSB first
            for (i = 8; i != 0; i--) // PWM each bit in assembly code for speed
            {
                asm("bsf LATA,4"); // Make N2 output high
                asm("nop"); // Wait one instruction cycle
                asm("btfss _temp,7"); // If MSB == 1, skip next instruction
                asm("bcf LATA,4"); // Make N2 output low (0)
                asm("lslf _temp,f"); // Shift next bit into MSB position
                asm("bcf LATA,4"); // Make N2 output low (1)
            }
            temp = 0; // Red next, same as green.
            for (i = 8; i != 0; i--) {
                asm("bsf LATA,4");
                asm("nop");
                asm("btfss _temp,7");
                asm("bcf LATA,4");
                asm("lslf _temp,f");
                asm("bcf LATA,4");
            }
            temp = 0; // Blue last. 
            for (i = 8; i != 0; i--) {
                asm("bsf LATA,4");
                asm("nop");
                asm("btfss _temp,7");
                asm("bcf LATA,4");
                asm("lslf _temp,f");
                asm("bcf LATA,4");
            }
        }
    }
}

//THE STARTING NEORGB FUNCTION GIVEN BY RAMPELT

void baseNeoRGB(unsigned char red, unsigned char green, unsigned char blue, unsigned char leds) {
    for (leds; leds != 0; leds--) // Repeat all 24 colour bits for each LED
    {
        temp = green; // Copy green byte, prepare to shift MSB first
        for (i = 8; i != 0; i--) // PWM each bit in assembly code for speed
        {
            asm("bsf LATA,4"); // Make N2 output high
            asm("nop"); // Wait one instruction cycle
            asm("btfss _temp,7"); // If MSB == 1, skip next instruction
            asm("bcf LATA,4"); // Make N2 output low (0)
            asm("lslf _temp,f"); // Shift next bit into MSB position
            asm("bcf LATA,4"); // Make N2 output low (1)
        }
        temp = red; // Red next, same as green.
        for (i = 8; i != 0; i--) {
            asm("bsf LATA,4");
            asm("nop");
            asm("btfss _temp,7");
            asm("bcf LATA,4");
            asm("lslf _temp,f");
            asm("bcf LATA,4");
        }
        temp = blue; // Blue last. 
        for (i = 8; i != 0; i--) {
            asm("bsf LATA,4");
            asm("nop");
            asm("btfss _temp,7");
            asm("bcf LATA,4");
            asm("lslf _temp,f");
            asm("bcf LATA,4");
        }
    }
}

//RUNS AN ARRAY OF LIGHTS (USED FOR BINARY COUNT FOR SENSORS)

void neoArray(unsigned char red[], unsigned char blue[], unsigned char green[]) {
    for (unsigned char index = 0; index < 8; index++) {
        temp = green[index]; // Copy green byte, prepare to shift MSB first
        for (i = 8; i != 0; i--) // PWM each bit in assembly code for speed
        {
            asm("bsf LATA,4"); // Make N2 output high
            asm("nop"); // Wait one instruction cycle
            asm("btfss _temp,7"); // If MSB == 1, skip next instruction
            asm("bcf LATA,4"); // Make N2 output low (0)
            asm("lslf _temp,f"); // Shift next bit into MSB position
            asm("bcf LATA,4"); // Make N2 output low (1)
        }
        temp = red[index]; // Red next, same as green.
        for (i = 8; i != 0; i--) {
            asm("bsf LATA,4");
            asm("nop");
            asm("btfss _temp,7");
            asm("bcf LATA,4");
            asm("lslf _temp,f");
            asm("bcf LATA,4");
        }
        temp = blue[index]; // Blue last. 
        for (i = 8; i != 0; i--) {
            asm("bsf LATA,4");
            asm("nop");
            asm("btfss _temp,7");
            asm("bcf LATA,4");
            asm("lslf _temp,f");
            asm("bcf LATA,4");
        }
    }
}

//LOADS ARRAY FOR BINARY COUNT OF LIGHT SENSOR / TEMP SENSOR BASED ON WHAT YOU SET "sensor" AS

void loadArray(unsigned char sensor) {

    for (unsigned char i = 8; i != 0; i--) {
        if ((sensor & 0b10000000) == 0b10000000) {
            redArray[i] = 255;
        } else {
            redArray[i] = 0;
        }
        sensor = sensor << 1;
    }

    //this is very, very, wrong I have no idea what I was thinking when writing this...
    //    redArray[0] = ((tempsense & 0b00000001) * 255);
    //    redArray[1] = ((tempsense & 0b00000010 >> 1) * 255);
    //    redArray[2] = ((tempsense & 0b00000100 >> 2) * 255);
    //    redArray[3] = ((tempsense & 0b00001000 >> 3) * 255);
    //    redArray[4] = ((tempsense & 0b00010000 >> 4) * 255);
    //    redArray[5] = ((tempsense & 0b00100000 >> 5) * 255);
    //    redArray[6] = ((tempsense & 0b01000000 >> 6) * 255);
    //    redArray[7] = ((tempsense & 0b10000000 >> 7) * 255);

    greenArray[0] = 0;
    greenArray[1] = 0;
    greenArray[2] = 0;
    greenArray[3] = 0;
    greenArray[4] = 0;
    greenArray[5] = 0;
    greenArray[6] = 0;
    greenArray[7] = 0;

    blueArray[0] = 0;
    blueArray[1] = 0;
    blueArray[2] = 0;
    blueArray[3] = 0;
    blueArray[4] = 0;
    blueArray[5] = 0;
    blueArray[6] = 0;
    blueArray[7] = 0;
}

int main(void) // Start of program
{
    init(); // Initialize oscillator and I/O Ports
    N1 = 0;
    //        loadArray();
    __delay_us(200);

    while (1) {
        //****FOR SOME REASON THE TEMPERATURE SENSOR PORT IS THE PHOTOTRANSISTOR PORT??****//

        unsigned char lightsense = adConvert(ADT1); //CALCULATE WHAT THE PHOTOTRANSISTOR IS OUTPUTTING EVERY CLOCK CYCLE
        unsigned char tempsense = adConvert(ADTIM); //CALCULATE WHAT THE TEMPERATURE SENSOR IS OUTPUTTING EVERY CLOCK CYCLE
        //unsigned char temp = tempsense * 0.48828125;

        loadArray(tempsense); //load array of leds that prints out binary count of either sensor


        //I HAVE MODIFIED THIS CODE MANY TIMES TO COME UP WITH THIS, WHICH IS CURRENTLY THE MOST EFFICIENT CODE I CAME UP WITH

                if (tempsense >= 128) { //makes colour red or blue based on how cold it is (going to modify this when I get the temperature sensor to work properly)
                    isred = true;     
                } else if (tempsense <= 128) {
                    isred = false;
                }
        
                if (lightsense >= 128) { //makes the leds brighter in the light and dimmer in the dark
                    if (colour >= 5 && colour <= 30) {
                        maxbrightness = 255;
                    }
                } else if (lightsense <= 128) {
                    if (colour >= 5 && colour <= 30) {
                        maxbrightness = 150;
                    }
                }
        
                colour += 5; //constantly increases the leds to make it look like a firefly flicker
        
                if (colour == maxbrightness) { //if led brightness equals either 150 or 255 depeding on "maxbrightness", set brightness back to 0
                    colour = 0;
                    if (colour == 0) { //if colour is 0 choose a different random led to run 
                       LED = rand() % 8;
                    }
                }
        
                if (isred) { //depending on boolean based off of temperature sensor, run either red or blue colours
                    neoRGB(colour, 0, 0, 8, LED); //for red
                } else {
                    neoRGB(0, 0, colour, 8, LED); // for blue
                }


        //temperature sensor test code
        //neoRGB(redArray, 0, 0, 8, LED);

        //light sensor test code
        //neoRGB(137, lightsense, 0, 8, LED);

        //run binary count for sensors
        //        neoArray(redArray, greenArray, blueArray);
        //if (temp < 15) {
        //    baseNeoRGB(255, 0, 0, 8);
        //} else {
        //   baseNeoRGB(0, 255, 0, 8);
        //}

        __delay_ms(20);
    }
}
