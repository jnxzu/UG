#include <stdio.h>

int main(){
	int x=11, y;
	asm volatile (
		".intel_syntax noprefix;"
		
		"mov eax, %1;"
		"mov ecx, 33;"
		"xor ebx, ebx;"
		"xor edx, edx;"
		
		"loop:"
		"dec ecx;"
		"jz nieend;"
		"shl eax, 1;"
		"jc increase;"
		"jmp reset;"
		
		"increase:"
		"inc ebx;"
		"jmp loop;"
		
		"reset:"
		"cmp edx, ebx;"
		"jg loop;"		
		"mov edx, ebx;"
		"xor ebx, ebx;"
		"jmp loop;"
		
		"nieend:"
		"cmp edx, ebx;"
		"jg end;"
		"mov edx, ebx;"
		
		"end:"
		"mov %0, edx;"
		".att_syntax prefix;"
		:"=r"(y)
		:"r"(x)
		:"eax", "ebx", "ecx", "edx"
	);
	
	printf("y = %i", y);
}
