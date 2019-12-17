.intel_syntax noprefix
.global main
.text

main:
	push offset xd
	push offset dolar
	call scanf
	
	add esp, 8
	
	mov eax, xd
	shl eax
	mov xd, eax
	
	push xd
	push offset dolar
	call printf
	
	add esp, 8
	
	call exit
	
.data
xd: .long 0
dolar: .asciz "%d"
