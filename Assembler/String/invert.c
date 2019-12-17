#include <stdio.h>

int main(){
	char s[] = "eimus w einadaz ot etsorp";
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
		"mov ebx, %1;"
		"mov esi, ebx;"
		"add ebx, ecx;"
		"mov edi, ebx;"
		"dec edi;"
		"lup:"
		"mov al, [esi];"
		"mov bl, [edi];"
		"mov [esi], bl;"
		"mov [edi], al;"
		"inc esi;"
		"dec edi;"
		"sub ecx, 2;"
		"jg lup;"

		//"mov %1, ebx;"

		".att_syntax prefix;"
		:"=r"(y)
		:"r"(s)
		:"ebx","ecx","al"
	);
	printf("%s\n", s);
}
