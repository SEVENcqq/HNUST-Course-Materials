data segment
  array  dw 0ffh,18h,8876h,5005,1008H,4321H
  n dw ?
data  ends
code  segment
  assume  cs:code,ds:data
main  proc  far
start:
   push  ds
   sub  ax, ax
   push  ax
   mov  ax, data ;送段地址
   mov  ds, ax

   mov  dx, 0 ;计数器
   mov  cx, 6 ;循环次数
   dec  cx	  
   lea  si, array ;获取array的偏移量
k: mov  ax, [si]  ;将该偏移量位置对应的数取出
   and  ax, 8000h  ;判断该数的符号
   mov  bx, [si+2] ;将下一个数取出
   and  bx, 8000h  ;也判断该数的符号
   cmp  ax, bx     ;比较两数的符号
   je   l		   ;如果相等跳转至l直接进行下一次比较
   inc dx		   ;如果不等说明符号有变化，进行计数
l:
   add  si, 2
   loop  k
   mov  n, dx
   mov ah,2
   add dl,30H
   int 21h
   ret
main  endp
code  ends
end   start