#include <stdio.h>

int main(){
	int x=21, y;
	asm volatile (
		".intel_syntax noprefix;"
		
		"mov eax, %1;"
		"mov ecx, 33;"
		"xor edx, edx;"
		"xor ebx, ebx;"
		
		"loop:"
		"dec ecx;"
		"jz end;"
		"mov ebx, eax;"
		"shl eax, 1;"
		"shr ebx, 29;"
		"cmp ebx, 5;"
		"je increase;"
		"jmp loop;"
		
		"increase:"
		"inc edx;"
		"jmp loop;"
		
		"end:"
		"mov %0, edx;"
		".att_syntax prefix;"
		:"=r"(y)
		:"r"(x)
		:"eax", "ebx", "ecx", "edx"
	);
	
	printf("y = %i", y);
}
