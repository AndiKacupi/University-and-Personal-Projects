int main()
{
int b,a,T_1,T_2,T_3,T_4;

L_2: if (a<=4) goto L_6 // <=,a,4,6
L_3: goto L_4 // jump,_,_,4
L_4: if (a<3) goto L_6 // <,a,3,6
L_5: goto L_10 // jump,_,_,10
L_6: T_1=3-2; // -,3,2,T_1
L_7: a=T_1; // :=,T_1,_,a
L_9: goto L_10 // jump,_,_,10
L_12: if (x>3) goto L_18 // >,x,3,18
L_13: goto L_14 // jump,_,_,14
L_14: if (v<>54) goto L_16 // <>,v,54,16
L_15: goto L_27 // jump,_,_,27
L_16: if (a<=567) goto L_18 // <=,a,567,18
L_17: goto L_27 // jump,_,_,27
L_18: T_2=3*5; // *,3,5,T_2
L_19: T_3=T_2/4; // /,T_2,4,T_3
L_20: T_4=T_3-5; // -,T_3,5,T_4
L_21: s=T_4; // :=,T_4,_,s
L_26: goto L_27 // jump,_,_,27
L_27: {} // halt,_,_,_
}
