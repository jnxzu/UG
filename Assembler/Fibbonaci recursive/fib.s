.intel_syntax noprefix

.global main
.text

main:
;#wczytanie
mov eax, [esp+8]
mov eax, [eax+4]
push eax
call atoi
add esp, 4 ;#powrot do poprzedniej pozycji

;#wywolanie f
push eax
call f
add esp, 4 ;#powrot do poprzedniej pozycji

;#wypisanie
push eax
mov ebx, offset res
push ebx
call printf
add esp, 8 ;#powrot do poprzedniej pozycji

;#reset eax
xor eax, eax
ret

f:	;#wywolanie dla n

	;#utworzenie stack frame (zakres danych obslugiwanych przez procedure)
    push ebp 
    mov  ebp, esp
    sub  esp, 4 ;#przygotowanie miejsca dla JEDNEJ zmiennej na stosie
    
    ;#obecny n -> eax
    mov  eax, [ebp+8]
    
    ;#if eax<2 koniec
    cmp  eax, 2
    jl   end
    
    ;#wywolanie fib dla n-1
    dec  eax
    push eax ;# NA ESP OBECNIE n-1
    call f
    
    ;#wywolanie fib dla n-2
    mov  [ebp-4], eax ;#zapamietanie n-1 w ebp
    dec  dword ptr [esp] ;#zmiana [esp](n-1) na format 32bit, zmiejszenie o 1
    call f
    add  esp, 4 ;#powrot do poprzedniej pozycji
    
    ;#sumuje n-ty i (n-1)ty element
    add  eax, [ebp-4]
end:
	;#zdjecie stack frame
    mov  esp, ebp
    pop  ebp 
    ret

.data

res: .asciz "%d\n"
