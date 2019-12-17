.intel_syntax noprefix
.global main
.text
main:
	mov eax, offset s
	push eax
	call printf
	pop eax
	ret

.data
s: .asciz "Hello, world\n"
