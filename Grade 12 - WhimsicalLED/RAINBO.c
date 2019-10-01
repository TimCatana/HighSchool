/*==============================================================================
	RAINBO (PIC12F1840) hardware initialization and user functions.
==============================================================================*/

#include	"xc.h"				// XC compiler general include file

#include	"stdint.h"			// Integer definition
#include	"stdbool.h"			// Boolean (true/false) definition

#include	"RAINBO.h"			// For optional user variable definitions

void init(void)
{
	// Initialize oscillator
	
	OSCCON = 0b11110000;		// 4xPLL with 8 MHz internal oscillator = 32 MHz
//	while(!PLLR);				// Wait until PLL is locked for timing
	
	// Initialize user ports and peripherals:

	OPTION_REG = 0b00010000;	// Weak pull-ups on, falling INT interrupt,
								// TRM0 internal, 1:2 pre-scaler
	PORTA = 0;					// Clear port input registers and output latches
	LATA = 0;					// before configuring port pins
	ANSELA = 0b00000011;		// Set RA0 and RA1 as analogue inputs
	ADCON0 = 0b00000000;		// Set up A-D: channel AN0, A-D converter off,
	ADCON1 = 0b01100000;		// left justified, FOSC/64 A-D clock, +VDD ref.
	TRISA = 0b00001111;			// Set RA2, RA3 as digital inputs, RA4-5 as outputs
	WPUA = 0b00001000;			// Enable weak pull-up on RA3 (S1 input)

	WDTCON = 0b00001010;		// WDT off, div 1024 (~32ms)

	// TODO - Enable and set up interrupts if needed
	
    INTCON = 0;                 // Keep interrupts disabled for now
}
