data segment
	data1 db 'CDE'
	data2 db 'ABCD'
data ends

esdata segment
	data3 db 7 dup(?),'$'
esdata ends

code segment
assume cs:code,ds:data,es:esdata
start:
	mov ax,data ;送段地址
	mov ds,ax
	mov ax,esdata
	mov es,ax
	mov si,offset data1 ;取变量的偏移量
	mov bx,offset data2
	mov di,offset data3
	mov cx,4 ;把ABCD送到data3前4个单元
	loop1:
		mov al,[bx]
		mov es:[di],al
		inc bx
		inc di
	loop loop1
	mov cx,3 ;把CDE送到data3后3个单元
	loop2:
		mov al,[si]
		mov es:[di],al
		inc si
		inc di
	loop loop2
	mov bx,es
	mov ds,bx
	lea dx,data3
	mov ah,09H
	int 21h
	mov ah,4ch ;退出
	int 21h
code ends
end start
