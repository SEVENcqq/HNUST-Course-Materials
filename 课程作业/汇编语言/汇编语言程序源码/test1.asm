DATA  SEGMENT   ;定义字数据DATA1，DATA2
   DATA1  DW  12H
   DATA2  DW  6AH
   DATA3  DW  ?
   DATA4  DW  ?
DATA  ENDS
CODE  SEGMENT
  ASSUME  CS:CODE, DS:DATA
MAIN  PROC  FAR
START:
	PUSH  DS
	SUB  AX, AX
	PUSH  AX            ;DS，0入栈
	MOV  AX, DATA
	MOV  DS, AX       ;设置数据段段地址
	MOV  BX, DATA1
	ADD  BX, DATA2    ;DATA1+DATA2
	MOV  DATA3, BX
	MOV  AX, DATA1
	SUB  AX, DATA2
	MOV  DATA4, AX
	RET
MAIN  ENDP
CODE  ENDS
END  START