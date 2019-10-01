/*==============================================================================
    RAINBO (PIC12F1840) symbolic constants for main and other user functions.
==============================================================================*/

// PORT I/O device definitions

#define Q1         	RA0			// Phototransistor light sensor input
#define T1         	RA1			// Temperature sensor (analogue) input
#define U2      	RA2			// IR demodulator input
#define IR      	RA2			// IR demodulator input
#define S1			RA3			// Switch S1 input
#define N2      	RA4			// Neopixel2 (J2) output
#define N1			RA5			// Neopixel1 (J1) output

// A-D Converter input channel definitions

#define AN0			0b00000000	// Phototransistor Q1 A-D channel input (Ch0)
#define ADQ1		0b00000000	// Phototransistor Q1 A-D channel input (Ch0) //Y
#define	AN1 		0b00000100	// Temperature sensor T1 A-D input (Ch1)  //Y
#define	ADT1		0b00000100	// Temperature sensor T1 A-D input (Ch1) //Y
#define AN3			0b00001100	// Unused A-D channel 3 input on N2 header //N
#define ADTIM		0b01110100	// PICmicro on-die temperature indicator module //Y
#define ADDAC       0b01111000  // DAC output //N
#define ADFVR       0b01111100  // Fixed voltage reference output //Y?

// Clock frequency for delay macros and simulation

#define _XTAL_FREQ	32000000	// Set clock frequency for time delays
#define FCY	_XTAL_FREQ/4        // Set processor instruction cycle time

// TODO - Add function prototypes for all functions in RAINBO.c here:

void init(void); // Initialization function prototype