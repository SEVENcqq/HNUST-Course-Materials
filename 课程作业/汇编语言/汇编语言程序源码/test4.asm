data_seg segment
	n dw 5
	ary dw 1,0,2,0,0
data_seg ends

code_seg segment
main proc far
assume cs:code_seg,ds:data_seg
start:
	push ds
	sub ax,ax
	push ax
	mov ax,data_seg
	mov ds,ax  ;送段地址
	mov cx,n   ;判断次数
	dec cx     
	mov bx,0
	mov di,0   ;偏移地址放置在di
end1:
	cmp ary[di],0  ;将偏移地址对应的数取出与0进行比较
	je move        ;如果相等跳转至move处
	add di,2	   
	dec cx
	jmp end1	
move:
	push cx         ;压栈保留数据
	push di	
k:
	mov dx,ary[di+2]
	mov ary[di],dx  ;将后一项往前移动一位
	add di,2
	loop k
	pop di
	pop cx
	inc bx
	loop end1
	mov cx,bx       ;将0的个数放置在CX
end2:
	add di,2
	mov ary[di],0   ;在ary中添加CX数量的0
	loop end2
	mov cx,n
	lea di,ary
print:
	mov dx,[di]
	add dl,30h
	mov ah,2
	int 21h
	add di,2
loop print			;循环打印元素
ret
main endp
code_seg ends
end start