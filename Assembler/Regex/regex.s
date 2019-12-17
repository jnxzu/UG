.intel_syntax noprefix
.global main
.text

.data
nope: .asciz "not found"
dolar: .asciz "%s\n"

main:
	mov eax, [esp+8]
	mov eax, [eax+4]
	mov ebx, eax

	mov ecx, 0

	match:
	mov al, [ebx]
	cmp al, 0x00
	je fail
	cmp al, 'a'
	je gonega
	cmp al, 'b'
	je gonega
	cmp al, 'z'
	je gonega
	inc ebx
	jmp match
	
	gonega:
	mov [edx+ecx], al
	inc ecx
	inc ebx
	mov al, [ebx]

	nega:
	cmp al, 0x00
	je fail
	cmp al, 'a'
	je reset
	cmp al, 'b'
	je reset
	cmp al, 'c'
	je reset
	mov [edx+ecx], al
	inc ecx
	inc ebx
	mov al, [ebx]
	cmp al, 'c'
	je golast
	jmp nega
	
	golast:
	mov [edx+ecx], al
	inc ecx
	mov al, 0x00
	mov [edx+ecx], al
	
	end:
	push edx
	push offset dolar
	call printf
	add esp, 8
	pop edx
	call exit
	
	fail:
	mov edx, offset nope
	jmp end
	
	reset:
	mov ecx, 0
	jmp match
