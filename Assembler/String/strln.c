#include <stdio.h>

int main(){
	char *s = "Abcdef";
	int y;
	asm volatile (
		".intel_syntax noprefix;"
		
		"mov ebx, %1;"
		"xor ecx, ecx;"
		
		"l00p:"
		"mov al, [ebx];"
		"cmp al, 0;"
		"je ded;"
		"inc ecx;"
		"inc ebx;"
		"jmp l00p;"
		
		"ded:"
		"mov %0, ecx;"
		
		".att_syntax prefix;"
		:"=r"(y)
		:"r"(s)
		:"eax","ebx","ecx","al"
	);
	printf("y = %i\n", y);
}
