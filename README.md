# BEPL
Binary Editor for Processor Language

BEPL is a program that converts your code into hexcode which an 8-bit processor (T2X8 architecture) can read. It's a programming language and a very basic editor in one.

## Example

You write:

LOAD ('Z', A)

This simply means: Load 'Z' into register A.

BEPL gives you:

00000000
01011010 (Z in binary)
