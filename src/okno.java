import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class okno extends JFrame{
	pole gameX = new pole();
	int hero, hp=100, mhp=100, mp=100, mmp=100, herolevel=1, exp=0, dmg,mdmg,maxl,minl,maxg,ming,rnd,l,g,repl,CampFinished,manacost;
	int sword,dual,bow,magic,koja,iron,gold,eqweapon,eqarmor,k,i,n,spd;
	String map="";
	String level="";
	String profile="";
	int menu=0; // 0-����, 1-���������, 2-������, 3-���������� ��������
	String[][] lol = new String[15][10];
	String spell="none";
	int[][] health = new int[15][10];
	int[][] maxhealth = new int[15][10];
	int[][] damage = new int[15][10];
	int[][] armor = new int[15][10];
	int[][] weapon = new int[15][10];
	int[][] inventory = new int[5][6];
	int inv_ram = 0;
	int skills_ram=0;
	String omg="";
	int gmo;
	int plusx=163,plusy=58,maxx=30,maxy=15;
	boolean Camp;
	boolean enemynear=false;
	boolean active = false;
	public final int vamp=15,xpow=35;
	int scrW,scrH;
	int rndclip;
	int invxx,invyy;
	int skillyy,skilllev,skillnum;
	String skillnamed;
	KeyListener keylis1;
	String name;
	public class myKey implements KeyListener
	{
		public void keyPressed(KeyEvent e){
			int key_ = e.getKeyCode();
			if(gameX.inMessage==0){
			gameX.ram=false;
			if(gameX.animation==0){
				if((key_==70)&(magic>=20)&(menu==0)){
					cold_embrace();
				}
				else if((key_==82)&(magic>=30)&(menu==0)){
					storm();
				}
				else if(key_==37){//�����
				if (menu==0){
				if (hero-(hero/15*15)>0){
					if (Integer.parseInt(lol[hero-(hero/15*15)-1][hero/15])==3){
						lol[hero-(hero/15*15)-1][hero/15]="0";
						lol[hero-(hero/15*15)][hero/15]="3";
						gameX.lol[hero-(hero/15*15)-1][hero/15]="0";
						gameX.lol[hero-(hero/15*15)][hero/15]="3";
						hero-=1;
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)-1][hero/15])==4){
						chest();
						lol[hero-(hero/15*15)-1][hero/15]="3";
						gameX.lol[hero-(hero/15*15)-1][hero/15]="3";
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)-1][hero/15])==5){
						if(eqweapon<=4){
							swordattack(hero,hero-1);
						}
						else if(eqweapon<=9){
							dualattack(hero,hero-1);
						}
						else{
							bowattack(hero,hero-1);
						}
						
						deathstrike(hero-1);
						
							bladeflurry();
						
						if(eqweapon<=4){
							rnd = (int)(Math.random()*100);
							if (rnd<15){
								dmg+=dmg;
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=6;
								gameX.anim[gameX.animation][1]=hero-hero/15*15-1;
								gameX.anim[gameX.animation][2]=hero/15;
							}
						}
						if((sword>=30)&(eqweapon<=4)){
							hp+=dmg*vamp/100;
							if(hp>mhp){hp=mhp;};
							gameX.hp=hp;
							gameX.mhp=mhp;
						}
						if(hero-hero/15*15>=2){
							if((sword>=20)&(Integer.parseInt(lol[hero-(hero/15*15)-2][hero/15])==5)){
								health[hero-(hero/15*15)-2][hero/15]-=dmg/2;
								gameX.health[hero-(hero/15*15)-2][hero/15]-=dmg/2;
								isDead(hero-2);
							}
						}
						damage();
						if ((eqweapon>=10)&(eqweapon<=14)&(bow<20)){
							health[hero-(hero/15*15)-1][hero/15]-=dmg/2;
							gameX.health[hero-(hero/15*15)-1][hero/15]-=dmg/2;
						}
						else{
						health[hero-(hero/15*15)-1][hero/15]-=dmg;
						gameX.health[hero-(hero/15*15)-1][hero/15]-=dmg;
						if((koja>=20)&((armor[hero-(hero/15*15)-1][hero/15]==0)|(armor[hero-(hero/15*15)-1][hero/15]==1))){
							health[hero-(hero/15*15)-1][hero/15]-=dmg/2;
							gameX.health[hero-(hero/15*15)-1][hero/15]=health[hero-(hero/15*15)-1][hero/15];
						}
						if((iron>=20)&((armor[hero-(hero/15*15)-1][hero/15]==2)|(armor[hero-(hero/15*15)-1][hero/15]==3))){
							health[hero-(hero/15*15)-1][hero/15]-=dmg*4/10;
							gameX.health[hero-(hero/15*15)-1][hero/15]=health[hero-(hero/15*15)-1][hero/15];
						}
						}
						isDead(hero-1);
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)-1][hero/15])==6){
						level_finished();
					}
					
				}
				}
				else if (menu==1){
					if (inv_ram-inv_ram/5*5>0){
						inv_ram-=1;
						gameX.inv_ram-=1;
					}
				}
				else if (menu==2){
					if (skills_ram-skills_ram/5*5>0){
						skills_ram-=1;
						gameX.skills_ram-=1;
					}
				}
			}
			else if(key_==38){//�����
				if(menu==0){
				if (hero/15>0){
					if (Integer.parseInt(lol[hero-(hero/15*15)][hero/15-1])==3){
						lol[hero-(hero/15*15)][hero/15-1]="0";
						lol[hero-(hero/15*15)][hero/15]="3";
						gameX.lol[hero-(hero/15*15)][hero/15-1]="0";
						gameX.lol[hero-(hero/15*15)][hero/15]="3";
						hero-=15;
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15-1])==4){
						chest();
						lol[hero-(hero/15*15)][hero/15-1]="3";
						gameX.lol[hero-(hero/15*15)][hero/15-1]="3";
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15-1])==5){
						if(eqweapon<=4){
							swordattack(hero,hero-15);
						}
						else if(eqweapon<=9){
							dualattack(hero,hero-15);
						}
						else{
							bowattack(hero,hero-15);
						}
						
						deathstrike(hero-15);
							bladeflurry();
						
						if(eqweapon<=4){
							rnd = (int)(Math.random()*100);
							if (rnd<15){
								dmg+=dmg;
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=6;
								gameX.anim[gameX.animation][1]=hero-hero/15*15;
								gameX.anim[gameX.animation][2]=hero/15-1;
							}
						}
						if((sword>=30)&(eqweapon<=4)){
							hp+=dmg*vamp/100;
							if(hp>mhp){hp=mhp;};
							gameX.hp=hp;
							gameX.mhp=mhp;
						}
						if(hero/15>=2){
							if((sword>=20)&(Integer.parseInt(lol[hero-(hero/15*15)][hero/15-2])==5)){
								health[hero-(hero/15*15)][hero/15-2]-=dmg/2;
								gameX.health[hero-(hero/15*15)][hero/15-2]-=dmg/2;
								isDead(hero-30);
							}
						}
						damage();
						if ((eqweapon>=10)&(eqweapon<=14)&(bow<20)){
							health[hero-(hero/15*15)][hero/15-1]-=dmg/2;
							gameX.health[hero-(hero/15*15)][hero/15-1]-=dmg/2;
						}
						else{
						health[hero-(hero/15*15)][hero/15-1]-=dmg;
						gameX.health[hero-(hero/15*15)][hero/15-1]-=dmg;
						if((koja>=20)&((armor[hero-(hero/15*15)][hero/15-1]==0)|(armor[hero-(hero/15*15)][hero/15-1]==1))){
							health[hero-(hero/15*15)][hero/15-1]-=dmg/2;
							gameX.health[hero-(hero/15*15)][hero/15-1]=health[hero-(hero/15*15)][hero/15-1];
						}
						if((iron>=20)&((armor[hero-(hero/15*15)][hero/15-1]==2)|(armor[hero-(hero/15*15)][hero/15-1]==3))){
							health[hero-(hero/15*15)][hero/15-1]-=dmg*4/10;
							gameX.health[hero-(hero/15*15)][hero/15-1]=health[hero-(hero/15*15)][hero/15-1];
						}
						}
						isDead(hero-15);
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15-1])==6){
						level_finished();
					}
					
				}
				}
				else if(menu==1){
					if (inv_ram>4){
						inv_ram-=5;
						gameX.inv_ram-=5;
					}
				}
				else if(menu==2){
					if (skills_ram>4){
						skills_ram-=5;
						gameX.skills_ram-=5;
					}
				}
			}
			else if(key_==39){//������
				if(menu==0){
				if (hero-(hero/15*15)<14){
					if (Integer.parseInt(lol[hero-(hero/15*15)+1][hero/15])==3){
						lol[hero-(hero/15*15)+1][hero/15]="0";
						lol[hero-(hero/15*15)][hero/15]="3";
						gameX.lol[hero-(hero/15*15)+1][hero/15]="0";
						gameX.lol[hero-(hero/15*15)][hero/15]="3";
						hero+=1;
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)+1][hero/15])==4){
						chest();
						lol[hero-(hero/15*15)+1][hero/15]="3";
						gameX.lol[hero-(hero/15*15)+1][hero/15]="3";
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)+1][hero/15])==5){
						if(eqweapon<=4){
							swordattack(hero,hero+1);
						}
						else if(eqweapon<=9){
							dualattack(hero,hero+1);
						}
						else{
							bowattack(hero,hero+1);
						}
						
						deathstrike(hero+1);
							bladeflurry();
						
						if(eqweapon<=4){
							rnd = (int)(Math.random()*100);
							if (rnd<15){
								dmg+=dmg;
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=6;
								gameX.anim[gameX.animation][1]=hero-hero/15*15+1;
								gameX.anim[gameX.animation][2]=hero/15;
							}
						}
						if((sword>=30)&(eqweapon<=4)){
							hp+=dmg*vamp/100;
							if(hp>mhp){hp=mhp;};
							gameX.hp=hp;
							gameX.mhp=mhp;
						}
						if(hero-hero/15*15<=12){
							if((sword>=20)&(Integer.parseInt(lol[hero-(hero/15*15)+2][hero/15])==5)){
								health[hero-(hero/15*15)+2][hero/15]-=dmg/2;
								gameX.health[hero-(hero/15*15)+2][hero/15]-=dmg/2;
								isDead(hero+2);
							}
						}
						damage();
						if ((eqweapon>=10)&(eqweapon<=14)&(bow<20)){
							health[hero-(hero/15*15)+1][hero/15]-=dmg/2;
							gameX.health[hero-(hero/15*15)+1][hero/15]-=dmg/2;
						}
						else{
						health[hero-(hero/15*15)+1][hero/15]-=dmg;
						gameX.health[hero-(hero/15*15)+1][hero/15]-=dmg;
						if((koja>=20)&((armor[hero-(hero/15*15)+1][hero/15]==0)|(armor[hero-(hero/15*15)+1][hero/15]==1))){
							health[hero-(hero/15*15)+1][hero/15]-=dmg/2;
							gameX.health[hero-(hero/15*15)+1][hero/15]=health[hero-(hero/15*15)+1][hero/15];
						}
						if((iron>=20)&((armor[hero-(hero/15*15)+1][hero/15]==2)|(armor[hero-(hero/15*15)+1][hero/15]==3))){
							health[hero-(hero/15*15)+1][hero/15]-=dmg*4/10;
							gameX.health[hero-(hero/15*15)+1][hero/15]=health[hero-(hero/15*15)+1][hero/15];
						}
						}
						isDead(hero+1);
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)+1][hero/15])==6){
						level_finished();
					}
					
				}
				}
				else if(menu==1){
					if (inv_ram-inv_ram/5*5<4){
						inv_ram+=1;
						gameX.inv_ram+=1;
					}
				}
				else if(menu==2){
					if (skills_ram-skills_ram/5*5<4){
						skills_ram+=1;
						gameX.skills_ram+=1;
					}
				}
				
			}
			else if(key_==40){//����
				if(menu==0){
				if (hero/15<9){
					if (Integer.parseInt(lol[hero-(hero/15*15)][hero/15+1])==3){
						lol[hero-(hero/15*15)][hero/15+1]="0";
						lol[hero-(hero/15*15)][hero/15]="3";
						gameX.lol[hero-(hero/15*15)][hero/15+1]="0";
						gameX.lol[hero-(hero/15*15)][hero/15]="3";
						hero+=15;
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15+1])==4){
						chest();
						lol[hero-(hero/15*15)][hero/15+1]="3";
						gameX.lol[hero-(hero/15*15)][hero/15+1]="3";
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15+1])==5){
						if(eqweapon<=4){
							swordattack(hero,hero+15);
						}
						else if(eqweapon<=9){
							dualattack(hero,hero+15);
						}
						else{
							bowattack(hero,hero+15);
						}
						
						deathstrike(hero+15);
							bladeflurry();
						
						if(eqweapon<=4){
							rnd = (int)(Math.random()*100);
							if (rnd<15){
								dmg+=dmg;
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=6;
								gameX.anim[gameX.animation][1]=hero-hero/15*15;
								gameX.anim[gameX.animation][2]=hero/15+1;
							}
						}
						if((sword>=30)&(eqweapon<=4)){
							hp+=dmg*vamp/100;
							if(hp>mhp){hp=mhp;};
							gameX.hp=hp;
							gameX.mhp=mhp;
						}
						if(hero/15<=7){
							if((sword>=20)&(Integer.parseInt(lol[hero-(hero/15*15)][hero/15+2])==5)){
								health[hero-(hero/15*15)][hero/15+2]-=dmg/2;
								gameX.health[hero-(hero/15*15)][hero/15+2]-=dmg/2;
								isDead(hero+30);
							}
						}
						damage();
						if ((eqweapon>=10)&(eqweapon<=14)&(bow<20)){
							health[hero-(hero/15*15)][hero/15+1]-=dmg/2;
							gameX.health[hero-(hero/15*15)][hero/15+1]-=dmg/2;
						}
						else{
						health[hero-(hero/15*15)][hero/15+1]-=dmg;
						gameX.health[hero-(hero/15*15)][hero/15+1]-=dmg;
						if((koja>=20)&((armor[hero-(hero/15*15)][hero/15+1]==0)|(armor[hero-(hero/15*15)][hero/15+1]==1))){
							health[hero-(hero/15*15)][hero/15+1]-=dmg/2;
							gameX.health[hero-(hero/15*15)][hero/15+1]=health[hero-(hero/15*15)][hero/15+1];
						}
						if((iron>=20)&((armor[hero-(hero/15*15)][hero/15+1]==2)|(armor[hero-(hero/15*15)][hero/15+1]==3))){
							health[hero-(hero/15*15)][hero/15+1]-=dmg*4/10;
							gameX.health[hero-(hero/15*15)][hero/15+1]=health[hero-(hero/15*15)][hero/15+1];
						}
						}
						isDead(hero+15);
						enemies();
					}
					else if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15+1])==6){
						level_finished();
					}
					
				}
			}
				else if(menu==1){
					if(inv_ram<25){
						inv_ram+=5;
						gameX.inv_ram+=5;
					}
				}
				else if(menu==2){
					if(skills_ram<30){
						skills_ram+=5;
						gameX.skills_ram+=5;
					}
				}
			}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				else if (key_==27){//ESC
					if(menu==0){
					gameX.inMessage=1;
					gameX.mes.setVisible(true);
					gameX.head.setVisible(true);
					gameX.textField.setVisible(false);
					gameX.head.setText("�����");
					gameX.mes.setText("<html>��������� � ������� ����?<br>��� �������� ��������� ���� ������� ������.</html>");
					}
					else if((menu==1)|(menu==2)){
						menu=0;
						gameX.menu=0;
					}
				}
				else if (key_==69){//E
					if((menu==0)|(menu==2)){
						menu=1;
						gameX.menu=1;
					}
					else if (menu==1){
						menu=2;
						gameX.menu=2;
					}
				}
				else if ((key_==32)|(key_==10)){//������
					spacebar();
				}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				else if((key_==87)&(eqweapon>=10)&(menu==0)){ // W
					
					if(hero/15==1){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=1;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero-15;
						if(Integer.parseInt(lol[hero-hero/15*15][0])==5){
							if (bow<20){
							health[hero-hero/15*15][0]-=dmg/2;
							gameX.health[hero-hero/15*15][0]-=dmg/2;
							}
							else{
								health[hero-hero/15*15][0]-=dmg;
								gameX.health[hero-hero/15*15][0]-=dmg;	
							}
							isDead(hero-15);
						}
					}
					else if(hero/15==2){
						if(Integer.parseInt(lol[hero-hero/15*15][1])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
							if (bow<30){
							health[hero-hero/15*15][1]-=dmg/2;
							gameX.health[hero-hero/15*15][1]-=dmg/2;
							}
							else{
								health[hero-hero/15*15][1]-=dmg;
								gameX.health[hero-hero/15*15][1]-=dmg;	
							}
							isDead(hero-15);
						}
						else if ((Integer.parseInt(lol[hero-hero/15*15][1])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][1])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-30;
							if (Integer.parseInt(lol[hero-hero/15*15][0])==5){
							
								health[hero-hero/15*15][0]-=dmg;
								gameX.health[hero-hero/15*15][0]-=dmg;	
							
							isDead(hero-30);
						}
						}
					}
					else if (hero/15>=3){
						if(Integer.parseInt(lol[hero-hero/15*15][hero/15-1])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
							if (bow<20){
							health[hero-hero/15*15][hero/15-1]-=dmg/2;
							gameX.health[hero-hero/15*15][hero/15-1]-=dmg/2;
							}
							else{
								health[hero-hero/15*15][hero/15-1]-=dmg;
								gameX.health[hero-hero/15*15][hero/15-1]-=dmg;	
							}
							isDead(hero-15);
						}
						else if((Integer.parseInt(lol[hero-hero/15*15][hero/15-1])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15-1])!=5){
							if(Integer.parseInt(lol[hero-hero/15*15][hero/15-2])==5){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero-30;
								health[hero-hero/15*15][hero/15-2]-=dmg;
								gameX.health[hero-hero/15*15][hero/15-2]-=dmg;	
							
								isDead(hero-30);
							}
							else if((Integer.parseInt(lol[hero-hero/15*15][hero/15-2])==2)&(bow<30)){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero-30;
							}
							else if (eqweapon>=11){
								if(Integer.parseInt(lol[hero-hero/15*15][hero/15-3])==5){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero-45;
									health[hero-hero/15*15][hero/15-3]-=dmg;
									gameX.health[hero-hero/15*15][hero/15-3]-=dmg;	
								
									isDead(hero-45);
								}
								else if((Integer.parseInt(lol[hero-hero/15*15][hero/15-3])==2)&(bow<30)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero-45;
								}
								else if((eqweapon<=13)|(hero/15<4)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero-45;	
								}
								else if(hero/15>=4){
									if(Integer.parseInt(lol[hero-hero/15*15][hero/15-4])==5){
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero-60;
										health[hero-hero/15*15][hero/15-4]-=dmg;
										gameX.health[hero-hero/15*15][hero/15-4]-=dmg;	
									
										isDead(hero-60);
									}
									else{
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero-60;
									}
								}
							}
							else{
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero-30;	
							}
							
						}
					}
					enemies();
				}
				else if((key_==87)&(magic>=1)&(menu==0)){
					manacost=30;
					if (eqarmor==13){
						manacost=manacost*125/100;
					}
					if((gold>=10)&(eqarmor>=10)){
						manacost=manacost*75/100;
					}
					if (mp>=manacost){
					if(hero/15==1){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=2;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero-15;
						mp-=manacost;
						gameX.mp=mp;
						if(Integer.parseInt(lol[hero-hero/15*15][0])==5){
								health[hero-hero/15*15][0]-=dmg;
								gameX.health[hero-hero/15*15][0]-=dmg;
								isDead(hero-15);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][0])==2){
							lol[hero-hero/15*15][0]="1";
							gameX.lol[hero-hero/15*15][0]="1";
						}
					}
					else if(hero/15==2){
						if(Integer.parseInt(lol[hero-hero/15*15][1])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][1]-=dmg;
								gameX.health[hero-hero/15*15][1]-=dmg;	
								isDead(hero-15);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][1])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][1]="1";
							gameX.lol[hero-hero/15*15][1]="1";
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][1])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-30;
							mp-=manacost;
							gameX.mp=mp;
							if (Integer.parseInt(lol[hero-hero/15*15][0])==5){
							
								health[hero-hero/15*15][0]-=dmg;
								gameX.health[hero-hero/15*15][0]-=dmg;	
							
								isDead(hero-30);
						}
						}
					}
					else if (hero/15>=3){
						if(Integer.parseInt(lol[hero-hero/15*15][hero/15-1])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][hero/15-1]-=dmg;
								gameX.health[hero-hero/15*15][hero/15-1]-=dmg;	
								isDead(hero-15);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][hero/15-1])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-15;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][hero/15-1]="1";
							gameX.lol[hero-hero/15*15][hero/15-1]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15-2])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-30;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][hero/15-2]-=dmg;
								gameX.health[hero-hero/15*15][hero/15-2]-=dmg;	
								isDead(hero-30);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15-2])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-30;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][hero/15-2]="1";
							gameX.lol[hero-hero/15*15][hero/15-2]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15-3])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-45;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][hero/15-3]-=dmg;
								gameX.health[hero-hero/15*15][hero/15-3]-=dmg;	
								isDead(hero-45);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15-3])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-45;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][hero/15-3]="1";
							gameX.lol[hero-hero/15*15][hero/15-3]="1";
						}
						else{
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-45;
							mp-=manacost;
							gameX.mp=mp;
							
						}
					}
					enemies();
					}
				}
				else if((key_==68)&(eqweapon>=10)&(menu==0)){ // D
					
					if(hero-hero/15*15==13){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=1;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero+1;
						if(Integer.parseInt(lol[14][hero/15])==5){
							if (bow<20){
							health[14][hero/15]-=dmg/2;
							gameX.health[14][hero/15]-=dmg/2;
							}
							else{
								health[14][hero/15]-=dmg;
								gameX.health[14][hero/15]-=dmg;	
							}
							isDead(hero+1);
						}
					}
					else if(hero-hero/15*15==12){
						if(Integer.parseInt(lol[13][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
							if (bow<30){
							health[13][hero/15]-=dmg/2;
							gameX.health[13][hero/15]-=dmg/2;
							}
							else{
								health[13][hero/15]-=dmg;
								gameX.health[13][hero/15]-=dmg;	
							}
							isDead(hero+1);
						}
						else if((Integer.parseInt(lol[13][hero/15])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
						}
						else if(Integer.parseInt(lol[13][hero/15])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+2;
							if (Integer.parseInt(lol[14][hero/15])==5){
							
								health[14][hero/15]-=dmg;
								gameX.health[14][hero/15]-=dmg;	
							
								isDead(hero+2);
						}
						}
					}
					else if (hero-hero/15*15<=11){
						if(Integer.parseInt(lol[hero-hero/15*15+1][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
							if (bow<30){
							health[hero-hero/15*15+1][hero/15]-=dmg/2;
							gameX.health[hero-hero/15*15+1][hero/15]-=dmg/2;
							}
							else{
								health[hero-hero/15*15+1][hero/15]-=dmg;
								gameX.health[hero-hero/15*15+1][hero/15]-=dmg;	
							}
							isDead(hero+1);
						}
						else if((Integer.parseInt(lol[hero-hero/15*15+1][hero/15])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
						}
						else if(Integer.parseInt(lol[hero-hero/15*15+1][hero/15])!=5){
							if(Integer.parseInt(lol[hero-hero/15*15+2][hero/15])==5){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero+2;
								health[hero-hero/15*15+2][hero/15]-=dmg;
								gameX.health[hero-hero/15*15+2][hero/15]-=dmg;	
							
								isDead(hero+2);
							}
							else if((Integer.parseInt(lol[hero-hero/15*15+2][hero/15])==2)&(bow<30)){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero+2;
							}
							else if (eqweapon>=11){
								if(Integer.parseInt(lol[hero-hero/15*15+3][hero/15])==5){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero+3;
									health[hero-hero/15*15+3][hero/15]-=dmg;
									gameX.health[hero-hero/15*15+3][hero/15]-=dmg;	
								
									isDead(hero+3);
								}
								else if((Integer.parseInt(lol[hero-hero/15*15+3][hero/15])==2)&(bow<30)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero+3;
								}
								else if((eqweapon<=13)|(hero-hero/15*15>10)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero+3;	
								}
								else if(hero-hero/15*15<=10){
									if(Integer.parseInt(lol[hero-hero/15*15+4][hero/15])==5){
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero+4;
										health[hero-hero/15*15+4][hero/15]-=dmg;
										gameX.health[hero-hero/15*15+4][hero/15]-=dmg;	
									
										isDead(hero+4);
									}
									else if((Integer.parseInt(lol[hero-hero/15*15+4][hero/15])==2)&(bow<30)){
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero+4;
									}
									else{
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero+4;
									}
								}
							}
							else{
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero+2;	
							}
							
						}
					}
					enemies();
				}
				else if((key_==68)&(magic>=1)&(menu==0)){

					manacost=30;
					if (eqarmor==13){
						manacost=manacost*125/100;
					}
					if((gold>=10)&(eqarmor>=10)){
						manacost=manacost*75/100;
					}
					if (mp>=manacost){
					if(hero-hero/15*15==13){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=2;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero+1;
						mp-=manacost;
						gameX.mp=mp;
						if(Integer.parseInt(lol[14][hero/15])==5){
								health[14][hero/15]-=dmg;
								gameX.health[14][hero/15]-=dmg;
								isDead(hero+1);
						}
						else if (Integer.parseInt(lol[14][hero/15])==2){
							lol[14][hero/15]="1";
							gameX.lol[14][hero/15]="1";
						}
					}
					else if(hero-hero/15*15==12){
						if(Integer.parseInt(lol[13][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
							mp-=manacost;
							gameX.mp=mp;
								health[13][hero/15]-=dmg;
								gameX.health[13][hero/15]-=dmg;	
								isDead(hero+1);
						}
						else if (Integer.parseInt(lol[13][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][1]="1";
							gameX.lol[hero-hero/15*15][1]="1";
						}
						else if (Integer.parseInt(lol[13][hero/15])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+2;
							mp-=manacost;
							gameX.mp=mp;
							if (Integer.parseInt(lol[14][hero/15])==5){
							
								health[14][hero/15]-=dmg;
								gameX.health[14][hero/15]-=dmg;	
							
								isDead(hero+2);
						}
						}
					}
					else if (hero-hero/15*15<=11){
						if(Integer.parseInt(lol[hero-hero/15*15+1][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15+1][hero/15]-=dmg;
								gameX.health[hero-hero/15*15+1][hero/15]-=dmg;	
								isDead(hero+1);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15+1][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+1;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15+1][hero/15]="1";
							gameX.lol[hero-hero/15*15+1][hero/15]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15+2][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+2;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15+2][hero/15]-=dmg;
								gameX.health[hero-hero/15*15+2][hero/15]-=dmg;	
								isDead(hero+2);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15+2][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+2;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15+2][hero/15]="1";
							gameX.lol[hero-hero/15*15+2][hero/15]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15+3][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+3;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15+3][hero/15]-=dmg;
								gameX.health[hero-hero/15*15+3][hero/15]-=dmg;	
								isDead(hero+3);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15+3][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+3;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15+3][hero/15]="1";
							gameX.lol[hero-hero/15*15+3][hero/15]="1";
						}
						else{
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+3;
							mp-=manacost;
							gameX.mp=mp;
						}
					}
					enemies();
					}
				}
				else if((key_==65)&(eqweapon>=10)&(menu==0)){ // A
					
					if(hero-hero/15*15==1){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=1;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero-1;
						if(Integer.parseInt(lol[0][hero/15])==5){
							if (bow<20){
							health[0][hero/15]-=dmg/2;
							gameX.health[0][hero/15]-=dmg/2;
							}
							else{
								health[0][hero/15]-=dmg;
								gameX.health[0][hero/15]-=dmg;	
							}
							isDead(hero-1);
						}
					}
					else if(hero-hero/15*15==2){
						if(Integer.parseInt(lol[1][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
							if (bow<30){
							health[1][hero/15]-=dmg/2;
							gameX.health[1][hero/15]-=dmg/2;
							}
							else{
								health[1][hero/15]-=dmg;
								gameX.health[1][hero/15]-=dmg;	
							}
							isDead(hero-1);
						}
						else if((Integer.parseInt(lol[1][hero/15])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
						}
						else if(Integer.parseInt(lol[1][hero/15])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-2;
							if (Integer.parseInt(lol[0][hero/15])==5){
							
								health[0][hero/15]-=dmg;
								gameX.health[0][hero/15]-=dmg;	
							
								isDead(hero-2);
						}
						}
					}
					else if (hero-hero/15*15>=3){
						if(Integer.parseInt(lol[hero-hero/15*15-1][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
							if (bow<30){
							health[hero-hero/15*15-1][hero/15]-=dmg/2;
							gameX.health[hero-hero/15*15-1][hero/15]-=dmg/2;
							}
							else{
								health[hero-hero/15*15-1][hero/15]-=dmg;
								gameX.health[hero-hero/15*15-1][hero/15]-=dmg;	
							}
							isDead(hero-1);
						}
						else if((Integer.parseInt(lol[hero-hero/15*15-1][hero/15])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
						}
						else if(Integer.parseInt(lol[hero-hero/15*15-1][hero/15])!=5){
							if(Integer.parseInt(lol[hero-hero/15*15-2][hero/15])==5){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero-2;
								health[hero-hero/15*15-2][hero/15]-=dmg;
								gameX.health[hero-hero/15*15-2][hero/15]-=dmg;	
							
								isDead(hero-2);
							}
							else if((Integer.parseInt(lol[hero-hero/15*15-2][hero/15])==2)&(bow<30)){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero-2;
							}
							else if (eqweapon>=11){
								if(Integer.parseInt(lol[hero-hero/15*15-3][hero/15])==5){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero-3;
									health[hero-hero/15*15-3][hero/15]-=dmg;
									gameX.health[hero-hero/15*15-3][hero/15]-=dmg;	
								
									isDead(hero-3);
								}
								else if((Integer.parseInt(lol[hero-hero/15*15-3][hero/15])==2)&(bow<30)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero-3;
								}
								else if((eqweapon<=13)|(hero-hero/15*15<4)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero-3;	
								}
								else if(hero-hero/15*15>=4){
									if(Integer.parseInt(lol[hero-hero/15*15-4][hero/15])==5){
										gameX.animation+=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero-4;
										health[hero-hero/15*15-4][hero/15]-=dmg;
										gameX.health[hero-hero/15*15-4][hero/15]-=dmg;	
									
										isDead(hero-4);
									}
									else if((Integer.parseInt(lol[hero-hero/15*15-4][hero/15])==2)&(bow<30)){
										gameX.animation+=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero-4;
									}
									else{
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero-4;
									}
								}
							}
							else{
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero-2;	
							}
							
						}
					}
					enemies();
				}

				else if((key_==65)&(magic>=1)&(menu==0)){

					manacost=30;
					if (eqarmor==13){
						manacost=manacost*125/100;
					}
					if((gold>=10)&(eqarmor>=10)){
						manacost=manacost*75/100;
					}
					if (mp>=manacost){
					if(hero-hero/15*15==1){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=2;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero-1;
						mp-=manacost;
						gameX.mp=mp;
						if(Integer.parseInt(lol[0][hero/15])==5){
								health[0][hero/15]-=dmg;
								gameX.health[0][hero/15]-=dmg;
								isDead(hero-1);
						}
						else if (Integer.parseInt(lol[0][hero/15])==2){
							lol[0][hero/15]="1";
							gameX.lol[0][hero/15]="1";
						}
					}
					else if(hero-hero/15*15==2){
						if(Integer.parseInt(lol[1][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
							mp-=manacost;
							gameX.mp=mp;
								health[1][hero/15]-=dmg;
								gameX.health[1][hero/15]-=dmg;	
								isDead(hero-1);
						}
						else if (Integer.parseInt(lol[1][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
							mp-=manacost;
							gameX.mp=mp;
							lol[1][hero/15]="1";
							gameX.lol[1][hero/15]="1";
						}
						else if (Integer.parseInt(lol[1][hero/15])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-2;
							mp-=manacost;
							gameX.mp=mp;
							if (Integer.parseInt(lol[0][hero/15])==5){
							
								health[0][hero/15]-=dmg;
								gameX.health[0][hero/15]-=dmg;	
							
								isDead(hero-2);
						}
						}
					}
					else if (hero-hero/15*15>=3){
						if(Integer.parseInt(lol[hero-hero/15*15-1][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15-1][hero/15]-=dmg;
								gameX.health[hero-hero/15*15-1][hero/15]-=dmg;	
								isDead(hero-1);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15-1][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-1;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15-1][hero/15]="1";
							gameX.lol[hero-hero/15*15-1][hero/15]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15-2][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-2;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15-2][hero/15]-=dmg;
								gameX.health[hero-hero/15*15-2][hero/15]-=dmg;	
								isDead(hero-2);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15-2][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-2;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15-2][hero/15]="1";
							gameX.lol[hero-hero/15*15-2][hero/15]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15-3][hero/15])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-3;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15-3][hero/15]-=dmg;
								gameX.health[hero-hero/15*15-3][hero/15]-=dmg;	
								isDead(hero-3);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15-3][hero/15])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-3;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15-3][hero/15]="1";
							gameX.lol[hero-hero/15*15-3][hero/15]="1";
						}
						else{
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero-3;
							mp-=manacost;
							gameX.mp=mp;
						}
					}
					enemies();
					}
				}
				else if((key_==83)&(eqweapon>=10)&(menu==0)){ // S
					
					if(hero/15==8){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=1;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero+15;
						if(Integer.parseInt(lol[hero-hero/15*15][9])==5){
							if (bow<20){
							health[hero-hero/15*15][9]-=dmg/2;
							gameX.health[hero-hero/15*15][9]-=dmg/2;
							}
							else{
								health[hero-hero/15*15][9]-=dmg;
								gameX.health[hero-hero/15*15][9]-=dmg;	
							}
							isDead(hero+15);
						}
					}
					else if(hero/15==7){
						if(Integer.parseInt(lol[hero-hero/15*15][8])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
							if (bow<30){
							health[hero-hero/15*15][8]-=dmg/2;
							gameX.health[hero-hero/15*15][8]-=dmg/2;
							}
							else{
								health[hero-hero/15*15][8]-=dmg;
								gameX.health[hero-hero/15*15][8]-=dmg;	
							}
							isDead(hero+15);						}
						else if((Integer.parseInt(lol[hero-hero/15*15][8])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][8])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+30;
							if (Integer.parseInt(lol[hero-hero/15*15][9])==5){
							
								health[hero-hero/15*15][9]-=dmg;
								gameX.health[hero-hero/15*15][9]-=dmg;	
							
								isDead(hero+30);
						}
						}
					}
					else if (hero/15<=6){
						if(Integer.parseInt(lol[hero-hero/15*15][hero/15+1])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
							if (bow<30){
							health[hero-hero/15*15][hero/15+1]-=dmg/2;
							gameX.health[hero-hero/15*15][hero/15+1]-=dmg/2;
							}
							else{
								health[hero-hero/15*15][hero/15+1]-=dmg;
								gameX.health[hero-hero/15*15][hero/15+1]-=dmg;	
							}
							isDead(hero+15);
						}
						else if((Integer.parseInt(lol[hero-hero/15*15][hero/15+1])==2)&(bow<30)){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15+1])!=5){
							if(Integer.parseInt(lol[hero-hero/15*15][hero/15+2])==5){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero+30;
								health[hero-hero/15*15][hero/15+2]-=dmg;
								gameX.health[hero-hero/15*15][hero/15+2]-=dmg;	
							
								isDead(hero+30);
							}
							else if((Integer.parseInt(lol[hero-hero/15*15][hero/15+2])==2)&(bow<30)){
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero+30;
							}
							else if (eqweapon>=11){
								if(Integer.parseInt(lol[hero-hero/15*15][hero/15+3])==5){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero+45;
									health[hero-hero/15*15][hero/15+3]-=dmg;
									gameX.health[hero-hero/15*15][hero/15+3]-=dmg;	
								
									isDead(hero+45);
								}
								else if((Integer.parseInt(lol[hero-hero/15*15][hero/15+3])==2)&(bow<30)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero+45;
								}
								else if((eqweapon<=13)|(hero/15>5)){
									gameX.animation+=1;
									gameX.anim[gameX.animation][0]=1;
									gameX.anim[gameX.animation][1]=hero;
									gameX.anim[gameX.animation][2]=hero+45;	
								}
								else if(hero/15<=5){
									if(Integer.parseInt(lol[hero-hero/15*15][hero/15+4])==5){
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero+60;
										health[hero-hero/15*15][hero/15+4]-=dmg;
										gameX.health[hero-hero/15*15][hero/15+4]-=dmg;	
									
										isDead(hero+60);
									}
									else if((Integer.parseInt(lol[hero-hero/15*15][hero/15+4])==2)&(bow<30)){
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero+60;
									}
									else{
										gameX.animation+=1;
										gameX.anim[gameX.animation][0]=1;
										gameX.anim[gameX.animation][1]=hero;
										gameX.anim[gameX.animation][2]=hero+60;
									}
								}
							}
							else{
								gameX.animation+=1;
								gameX.anim[gameX.animation][0]=1;
								gameX.anim[gameX.animation][1]=hero;
								gameX.anim[gameX.animation][2]=hero+30;	
							}
							
						}
					}
					enemies();
				}

				else if((key_==83)&(magic>=1)&(menu==0)){
					manacost=30;
					if (eqarmor==13){
						manacost=manacost*125/100;
					}
					if((gold>=10)&(eqarmor>=10)){
						manacost=manacost*75/100;
					}
					if (mp>=manacost){
					if(hero/15==8){
						gameX.animation+=1;
						gameX.anim[gameX.animation][0]=2;
						gameX.anim[gameX.animation][1]=hero;
						gameX.anim[gameX.animation][2]=hero+15;
						mp-=manacost;
						gameX.mp=mp;
						if(Integer.parseInt(lol[hero-hero/15*15][9])==5){
								health[hero-hero/15*15][9]-=dmg;
								gameX.health[hero-hero/15*15][9]-=dmg;
								isDead(hero+15);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][9])==2){
							lol[hero-hero/15*15][9]="1";
							gameX.lol[hero-hero/15*15][9]="1";
						}
					}
					else if(hero/15==7){
						if(Integer.parseInt(lol[hero-hero/15*15][8])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][8]-=dmg;
								gameX.health[hero-hero/15*15][8]-=dmg;	
								isDead(hero+15);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][8])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][8]="1";
							gameX.lol[hero-hero/15*15][8]="1";
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][8])!=5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+30;
							mp-=manacost;
							gameX.mp=mp;
							if (Integer.parseInt(lol[hero-hero/15*15][9])==5){
							
								health[hero-hero/15*15][9]-=dmg;
								gameX.health[hero-hero/15*15][9]-=dmg;	
							
								isDead(hero+30);
						}
						}
					}
					else if (hero/15<=7){
						if(Integer.parseInt(lol[hero-hero/15*15][hero/15+1])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][hero/15+1]-=dmg;
								gameX.health[hero-hero/15*15][hero/15+1]-=dmg;	
								isDead(hero+15);
						}
						else if (Integer.parseInt(lol[hero-hero/15*15][hero/15+1])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+15;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][hero/15+1]="1";
							gameX.lol[hero-hero/15*15][hero/15+1]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15+2])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+30;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][hero/15+2]-=dmg;
								gameX.health[hero-hero/15*15][hero/15+2]-=dmg;	
								isDead(hero+30);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15+2])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+30;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][hero/15+2]="1";
							gameX.lol[hero-hero/15*15][hero/15+2]="1";
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15+3])==5){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+45;
							mp-=manacost;
							gameX.mp=mp;
								health[hero-hero/15*15][hero/15+3]-=dmg;
								gameX.health[hero-hero/15*15][hero/15+3]-=dmg;	
								isDead(hero+45);
						}
						else if(Integer.parseInt(lol[hero-hero/15*15][hero/15+3])==2){
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+45;
							mp-=manacost;
							gameX.mp=mp;
							lol[hero-hero/15*15][hero/15+3]="1";
							gameX.lol[hero-hero/15*15][hero/15+3]="1";
						}
						else{
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=2;
							gameX.anim[gameX.animation][1]=hero;
							gameX.anim[gameX.animation][2]=hero+45;
							mp-=manacost;
							gameX.mp=mp;
						}
					}
					enemies();
					}
				}
			
		}
		}
		else if(gameX.inMessage==1){
			if(key_==10){
				clearMessage();
				stop();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==2){
			if((key_==10)|(key_==27)){
				clearMessage();
				stop();
			}
		}
		else if(gameX.inMessage==3){
			if((key_==10)|(key_==27)){
				clearMessage();
			}
		}
		else if(gameX.inMessage==4){
			if((key_==10)|(key_==27)){
				if((Camp==true)&(CampFinished==0)&(Integer.parseInt(level)==25)){
					gameX.inMessage=5;
					gameX.mes.setVisible(true);
					gameX.head.setVisible(true);
					gameX.textField.setVisible(false);
					gameX.head.setText("������� �������!");
					gameX.mes.setText("<html>�� ��������� ������ ��� ��������!<br>������ �� ������ ������ � ������ � ���������� ����������.<br>�������� ����� �������, �� �� �������� ���-��� ������!</html>");
				}
				else if((Camp==true)&(CampFinished==1)&(Integer.parseInt(level)==25)){
					gameX.inMessage=5;
					gameX.mes.setVisible(true);
					gameX.head.setVisible(true);
					gameX.textField.setVisible(false);
					gameX.head.setText("������� �������!");
					gameX.mes.setText("<html>�� ��������� ������ ��� �������� ������!<br>������ �� ������ ������� � ������ ����� ������� ��������.<br>��������� ����������� ��������!</html>");
				}
				else{
				setVisible(false);
				if((Camp==true)&(Integer.parseInt(level)<25)&(CampFinished<=1)){
					gameX.musicThread.closeall();
					gameX.timerDraw.stop();
					start("",true);
					
				}
				else{
					stop();
				}
				gameX.stop=true;
				}
			}
		}
		else if(gameX.inMessage==5){
			if((key_==10)|(key_==27)){
				setVisible(false);
				if((Camp==true)&(Integer.parseInt(level)<25)&(CampFinished<=1)){
					gameX.musicThread.closeall();
					start("",true);
				}
				else{
					stop();
				}
				gameX.stop=true;
			}
		}
		else if(gameX.inMessage==6){
			if(key_==10){
				if(invyy<=2){
					eqweapon=invxx+invyy*5;
					gameX.eqweapon=eqweapon;
					damage();
					clearMessage();
				}
				else{
					eqarmor=invxx+(invyy-3)*5;
					gameX.eqarmor=eqarmor;
					damage();
					clearMessage();
				}
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==7){
			if(key_==10){
				inventory[invxx][0]-=2;
				inventory[invxx][1]+=1;
				gameX.inv[invxx][0]=inventory[invxx][0];
				gameX.inv[invxx][1]=inventory[invxx][1];
				if(inventory[invxx][0]==0){
					eqweapon=invxx+5;
					gameX.eqweapon=eqweapon;
				}
				damage();
				clearMessage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==8){
			if(key_==10){
				if(sword<30){
					exp-=1;
					sword+=1;
					gameX.sword+=1;
					damage();
					if((sword==1)|(sword==10)|(sword==20)|(sword==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==9){
			if(key_==10){
				if(dual<30){
					exp-=1;
					dual+=1;
					gameX.dual+=1;
					damage();
					if((dual==1)|(dual==10)|(dual==20)|(dual==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==10){
			if(key_==10){
				if(bow<30){
					exp-=1;
					bow+=1;
					gameX.bow+=1;
					damage();
					if((bow==1)|(bow==10)|(bow==20)|(bow==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==11){
			if(key_==10){
				if(magic<30){
					exp-=1;
					magic+=1;
					gameX.magic+=1;
					damage();
					if((magic==1)|(magic==10)|(magic==20)|(magic==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==12){
			if(key_==10){
				if(koja<30){
					exp-=1;
					koja+=1;
					gameX.koja+=1;
					damage();
					if((koja==1)|(koja==10)|(koja==20)|(koja==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==13){
			if(key_==10){
				if(iron<30){
					exp-=1;
					iron+=1;
					gameX.iron+=1;
					damage();
					if((iron==1)|(iron==10)|(iron==20)|(iron==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==14){
			if(key_==10){
				if(gold<30){
					exp-=1;
					gold+=1;
					gameX.gold+=1;
					damage();
					if((gold==1)|(gold==10)|(gold==20)|(gold==30)){
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("�������� ������� ������");
						gameX.mes.setText("<html>�������� ����� ������!</html>");	
					}
					else{
						clearMessage();
					}
				}
				else{
					mlvl();
				}
				damage();
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==15){
			if(key_==10){
				exp-=(skilllev-skillnum);
				gameX.exp=exp;
				if(skillyy==0){
					sword=skilllev;
					gameX.sword=sword;
				}
				else if(skillyy==1){
					dual=skilllev;
					gameX.dual=dual;
				}
				else if(skillyy==2){
					bow=skilllev;
					gameX.bow=bow;
				}
				else if(skillyy==3){
					magic=skilllev;
					gameX.magic=magic;
				}
				else if(skillyy==4){
					koja=skilllev;
					gameX.koja=koja;
				}
				else if(skillyy==5){
					iron=skilllev;
					gameX.iron=iron;
				}
				else if(skillyy==6){
					gold=skilllev;
					gameX.gold=gold;
				}
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText(skillnamed);
				gameX.mes.setText("<html>������ �������!</html>");
			}
			else if(key_==27){
				clearMessage();
			}
		}
		else if(gameX.inMessage==16){
			if(key_==10){
				name=Integer.toString((int)(Math.random()*24));
				if (Integer.parseInt(name)<=9){
					name="0"+name;
				}
				clearMessage();
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>������� "+name+"</html>");
				loadMap();
			}
			else if(key_==27){
				gameX.inMessage=17;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(true);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>������ �� ������ ���� �������, ����� ������� �������� ������!<br>������ ������� ��� ����� (�� 0 �� 24).</html>");
				gameX.textField.setText("");
				gameX.textField.requestFocus();
				gameX.textField.addKeyListener(keylis1);
			}
		}
		else if(gameX.inMessage==18){
			if((key_==10)|(key_==27)){
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>�� ������ ��������� ����������, ���� ��������� � ���� � ������� ������� ����������� � ��� �������. ��������� - ���� ���� �������� (������� �������) ������ ���� ����, �� ����������. � ������ ���������� �������� � ����� ������ ���!</html>");
			}
		}
		else if(gameX.inMessage==19){
			if((key_==10)|(key_==27)){
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>���������� � ����� ����� ��������� ��� ��������. ����������� �������, ����� �������� �� �� �����, � ��������� �������� - � ������� ��� ���� ���� ���������� �����.</html>");
			}
		}
			
	}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	public okno(){
		Dimension sSize =Toolkit.getDefaultToolkit ().getScreenSize ();
		scrW=(int)(sSize.getWidth());
		scrH=(int)(sSize.getHeight());
		addKeyListener(new myKey());
		//setBounds(0,0,1520,838);
		setSize (sSize);
		setUndecorated(true);
		setTitle("Pasan");
		gameX = new pole();
		gameX.setLayout(null);
		Container con = getContentPane();
		con.add(gameX);
		gameX.musicThread=new music2();
		ImageIcon icon = new ImageIcon("./Pasan_Textures/Hero.png");
		setIconImage(icon.getImage());
		setVisible(false);
		this.addWindowListener(new WindowAdapter() {            
            @Override
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }
      });
		keylis1 = new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				int key=e.getKeyCode();
				if(key==10){
					String campName = gameX.textField.getText().trim();
					try{
					if ((Integer.parseInt(campName)>=0)&(Integer.parseInt(campName)<=24)){
						name=campName;
						if (Integer.parseInt(name)<=9){
							name="0"+name;
						}
						gameX.inMessage=3;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("��������");
						gameX.mes.setText("<html>������� "+name+"</html>");
						requestFocus();
						loadMap();
					}
					else{
						gameX.inMessage=16;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("������");
						gameX.mes.setText("<html>�� ����� ����� ������ �����������.<br>������� ��������� �������?</html>");
						gameX.textField.setText("");
						gameX.textField.removeKeyListener(keylis1);
						requestFocus();
					}
					}
					catch(Exception ex){
						gameX.inMessage=16;
						gameX.mes.setVisible(true);
						gameX.head.setVisible(true);
						gameX.textField.setVisible(false);
						gameX.head.setText("������");
						gameX.mes.setText("<html>�� ����� ����� ������ �����������.<br>������� ��������� �������?</html>");
						gameX.textField.setText("");
						gameX.textField.removeKeyListener(keylis1);
						requestFocus();
					}
				}
				else if(key==27){
					clearMessage();
					stop();
				}
			}
		};	
		
}
	public void enemies(){
		if (hero-(hero/15*15)>=3){minl=hero-(hero/15*15)-3;}
		else {minl=0;}
		if (hero-(hero/15*15)<=11){maxl=hero-(hero/15*15)+3;}
		else {maxl=14;}
		if (hero/15>=3){ming=hero/15-3;}
		else {ming=0;}
		if (hero/15<=6){maxg=hero/15+3;}
		else {maxg=9;}
		
		for (l=minl; l<=maxl; l++){
			for(g=ming; g<=maxg; g++){
				if(Integer.parseInt(lol[l][g])==5){// ���� ����� ����������
					//if (enemynear==false){enemynear=true;};
					if ((l<hero-(hero/15*15))&(g<hero/15)){ // ������ �����
						if ((Integer.parseInt(lol[l+1][g])==3)&(Integer.parseInt(lol[l][g+1])!=3)){ // ������ ��������, ����� ������ => ���� �������
							napravo();
						}
						else if ((Integer.parseInt(lol[l][g+1])==3)&(Integer.parseInt(lol[l+1][g])!=3)){ // ����� ��������, ������ ������ => ���� ����
							vniz();
						}
						else if ((Integer.parseInt(lol[l+1][g])==3)&(Integer.parseInt(lol[l][g+1])==3)){ // ������ ��������, ����� �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� �������
								napravo();
							}
							else{ // ���� ����
								vniz();
							}
						}
						 
					}
					else if ((l>hero-(hero/15*15))&(g<hero/15)){ // ������ ������
						if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l][g+1])!=3)){ // ����� ��������, ����� ������ => ���� ������
							nalevo();
						}
						else if ((Integer.parseInt(lol[l][g+1])==3)&(Integer.parseInt(lol[l-1][g])!=3)){ // ����� ��������, ����� ������ => ���� ����
							vniz();
						}
						else if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l][g+1])==3)){ // ����� ��������, ����� �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� ������
								nalevo();
							}
							else { // ���� ����
								vniz();
							}
						}
					}
					else if ((l<hero-(hero/15*15))&(g>hero/15)){ // ����� �����
						if ((Integer.parseInt(lol[l+1][g])==3)&(Integer.parseInt(lol[l][g-1])!=3)){ // ������ ��������, ������ ������ => ���� �������
							napravo();
						}
						else if ((Integer.parseInt(lol[l][g-1])==3)&(Integer.parseInt(lol[l+1][g])!=3)){ // ������ ��������, ������� ������ => ���� ������
							vverh();
						}
						else if ((Integer.parseInt(lol[l+1][g])==3)&(Integer.parseInt(lol[l][g-1])==3)){ // ������ ��������, ������ �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� �������
								napravo();
							}
							else{ // ���� �����
								vverh();
							}
							
						}
					}
					else if ((l>hero-(hero/15*15))&(g>hero/15)){ // ����� ������
						if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l][g-1])!=3)){ // ����� ��������, ������ ������ => ���� ������
							nalevo();
						}
						else if ((Integer.parseInt(lol[l][g-1])==3)&(Integer.parseInt(lol[l-1][g])!=3)){ // ������ ��������, ������ ������ => ���� ������
							vverh();
						}
						else if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l][g-1])==3)){ // ����� ��������, ������ �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� ������
								nalevo();
							}
							else { // ���� �����
								vverh();
							}
					}
					}
					else if ((l==hero-(hero/15*15))&(g<hero/15)){ // ������
						if ((Integer.parseInt(lol[l][g+1])==0)&(weapon[l][g]!=3)){// ���� ���, ����� ����� => �������
							if(weapon[l][g]==1){
							swordattack(l+g*15,hero);
							}
							else if(weapon[l][g]==2){
								dualattack(l+g*15,hero);	
							}
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((Integer.parseInt(lol[l][g+1])==0)&(weapon[l][g]==3)){ // ��� ����, ����� ����� => ������� � 50% ������
							bowattack(l+g*15,hero);
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/200;
							gameX.hp-=damage[l][g]*(100-k)/200;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							
							}
							else{
								missanim();
							}
						}
						else if ((g<8)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l][g+1])!=2)&(Integer.parseInt(lol[l][g+2])==0))){ // ��� ����, ����� ��������, ������ ����� - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if ((g<7)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l][g+1])!=2)&(Integer.parseInt(lol[l][g+2])!=2)&(Integer.parseInt(lol[l][g+3])==0))){ // ��� ����, 2 ����� ��������, ������ - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if (Integer.parseInt(lol[l][g+1])==3){ // ����� �������� => ���� ����
							vniz();
						}
						else if ((Integer.parseInt(lol[l-1][g])!=3)&(Integer.parseInt(lol[l+1][g])==3)){ // ������ ������, ������ �������� => ���� �������
							napravo();
						}
						else if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l+1][g])!=3)){ // ������ ��������, ������� ������ => ���� ������
							nalevo();
						}
						else if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l+1][g])==3)){ // ������ ��������, ������ �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� ������
								nalevo();
							}
							else{ // ���� �������
								napravo();
							}
						}
					}
					else if ((l==hero-(hero/15*15))&(g>hero/15)){ // �����
						if ((Integer.parseInt(lol[l][g-1])==0)&(weapon[l][g]!=3)){ // ���� ���, ������ ����� => �������
							if(weapon[l][g]==1){
								swordattack(l+g*15,hero);
								}
								else if(weapon[l][g]==2){
									dualattack(l+g*15,hero);	
								}
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((Integer.parseInt(lol[l][g-1])==0)&(weapon[l][g]==3)){ // ��� ����, ������ ����� => ������� � 50% ������
							bowattack(l+g*15,hero);
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/200;
							gameX.hp-=damage[l][g]*(100-k)/200;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((g>1)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l][g-1])!=2)&(Integer.parseInt(lol[l][g-2])==0))){ // ��� ����, ������ ��������, ������ ������ - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
							//System.out.println("�����, ��� ����, �� 2� ������ ������ �����, �������");
						}
						else if ((g>2)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l][g-1])!=2)&(Integer.parseInt(lol[l][g-2])!=2)&(Integer.parseInt(lol[l][g-3])==0))){ // ��� ����, 2 ������ ��������, ������ - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if (Integer.parseInt(lol[l][g-1])==3){ // ������ �������� => ���� ������
							vverh();
						}
						else if ((Integer.parseInt(lol[l-1][g])!=3)&(Integer.parseInt(lol[l+1][g])==3)){ // ������ ������, ������ �������� => ���� �������
							napravo();
						}
						else if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l+1][g])!=3)){ // ������ ��������, ������� ������ => ���� ������
							nalevo();
						}
						else if ((Integer.parseInt(lol[l-1][g])==3)&(Integer.parseInt(lol[l+1][g])==3)){ // ������ ��������, ������ �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� ������
								nalevo();
							}
							else{ // ���� �������
								napravo();
							}
						}
					}
					else if ((l<hero-(hero/15*15))&(g==hero/15)){ // �����
						if ((Integer.parseInt(lol[l+1][g])==0)&(weapon[l][g]!=3)){ // ���� ���, ������ ����� => �������
							if(weapon[l][g]==1){
								swordattack(l+g*15,hero);
								}
								else if(weapon[l][g]==2){
									dualattack(l+g*15,hero);	
								}
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((Integer.parseInt(lol[l+1][g])==0)&(weapon[l][g]==3)){ // ��� ����, ������ ����� => ������� � 50% ������
							bowattack(l+g*15,hero);
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/200;
							gameX.hp-=damage[l][g]*(100-k)/200;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((l<13)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l+1][g])!=2)&(Integer.parseInt(lol[l+2][g])==0))){ // ��� ����, ������ ��������, ������ ������ - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if ((l<12)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l+1][g])!=2)&(Integer.parseInt(lol[l+2][g])!=2)&(Integer.parseInt(lol[l+3][g])==0))){ // ��� ����, 2 ������ ��������, ������ - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if (Integer.parseInt(lol[l+1][g])==3){ // ������ �������� => ���� �������
							napravo();
						}
						else if ((Integer.parseInt(lol[l][g-1])!=3)&(Integer.parseInt(lol[l][g+1])==3)){ // ������ ������, ����� �������� => ���� ����
							vniz();
						}
						else if ((Integer.parseInt(lol[l][g-1])==3)&(Integer.parseInt(lol[l][g+1])!=3)){ // ������ ��������, ���� ������ => ���� ������
							vverh();
						}
						else if ((Integer.parseInt(lol[l][g-1])==3)&(Integer.parseInt(lol[l][g+1])==3)){ // ������ ��������, ���� �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� ������
								vverh();
							}
							else{ // ���� ����
								vniz();
							}
						}
					}
					else if ((l>hero-(hero/15*15))&(g==hero/15)){ // ������
						if ((Integer.parseInt(lol[l-1][g])==0)&(weapon[l][g]!=3)){ // ���� ���, ����� ����� => �������
							if(weapon[l][g]==1){
								swordattack(l+g*15,hero);
								}
								else if(weapon[l][g]==2){
									dualattack(l+g*15,hero);	
								}
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((Integer.parseInt(lol[l-1][g])==0)&(weapon[l][g]==3)){ // ��� ����, ����� ����� => ������� � 50% ������
							bowattack(l+g*15,hero);
							evasionmelee();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockmelee();
							hp-=damage[l][g]*(100-k)/200;
							gameX.hp-=damage[l][g]*(100-k)/200;
							if(eqarmor==13){
								health[l][g]-=gold;
								gameX.health[l][g]=health[l][g];
								isDead(l+g*15);
							}
							}
							else{
								missanim();
							}
						}
						else if ((l>1)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l-1][g])!=2)&(Integer.parseInt(lol[l-2][g])==0))){ // ��� ����, ����� ��������, ������ ����� - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if ((l>2)&&((weapon[l][g]==3)&(Integer.parseInt(lol[l-1][g])!=2)&(Integer.parseInt(lol[l-2][g])!=2)&(Integer.parseInt(lol[l-3][g])==0))){ // ��� ����, 2 ����� ��������, ������ - ����� => �������
							gameX.animation+=1;
							gameX.anim[gameX.animation][0]=1;
							gameX.anim[gameX.animation][1]=l+g*15;
							gameX.anim[gameX.animation][2]=hero;	
							evasionrange();
							rnd=(int)(Math.random()*100);
							if (rnd>=k){
								blockrange();
							hp-=damage[l][g]*(100-k)/100;
							gameX.hp-=damage[l][g]*(100-k)/100;
							}
							else{
								missanim();
							}
						}
						else if (Integer.parseInt(lol[l-1][g])==3){ // ����� �������� => ���� ������
							nalevo();
						}
						else if ((Integer.parseInt(lol[l][g-1])!=3)&(Integer.parseInt(lol[l][g+1])==3)){ // ������ ������, ����� �������� => ���� ����
							vniz();
						}
						else if ((Integer.parseInt(lol[l][g-1])==3)&(Integer.parseInt(lol[l][g+1])!=3)){ // ������ ��������, ���� ������ => ���� ������
							vverh();
						}
						else if ((Integer.parseInt(lol[l][g-1])==3)&(Integer.parseInt(lol[l][g+1])==3)){ // ������ ��������, ���� �������� => ������
							rnd = (int)(Math.random()*100);
							if (rnd>=50){ // ���� ������

								vverh();
							}
							else{ // ���� ����
								vniz();
							}
						}
					}
				}
			}
		
		}
		for (l=minl; l<=maxl; l++){
			for(g=ming; g<=maxg; g++){
				if(Integer.parseInt(lol[l][g])==7){
					lol[l][g]="5";
				}
			}
		}
		if(hp<=0){
			gameX.inMessage=2;
			gameX.mes.setVisible(true);
			gameX.head.setVisible(true);
			gameX.textField.setVisible(false);
			gameX.head.setText("���������");
			gameX.mes.setText("<html>��������� �������� �������...</html>");
		}
		enemynear=false;
		for (l=minl; l<=maxl; l++){
			for(g=ming; g<=maxg; g++){
				if(Integer.parseInt(lol[l][g])==5){// ���� ����� ����������
					enemynear=true;
				}
			}
		}
		if (enemynear==true){
			enemynear=false;
			if((iron==30)&(eqarmor>=5)&(eqarmor<=9)){
				hp+=5;
				if (hp>mhp){
					hp=mhp;
				}
				gameX.hp=hp;
			}
		}
	}
	public void vverh(){
		gameX.animation++;
		gameX.anim[gameX.animation][0]=0;
		gameX.anim[gameX.animation][1]=2;
		gameX.anim[gameX.animation][2]=l+g*15;
		lol[l][g-1]="7";
		lol[l][g]="3";
		health[l][g-1]=health[l][g];
		maxhealth[l][g-1]=maxhealth[l][g];
		damage[l][g-1]=damage[l][g];
		armor[l][g-1]=armor[l][g];
		weapon[l][g-1]=weapon[l][g];
		/*health[l][g]=0;
		damage[l][g]=0;
		armor[l][g]=0;
		weapon[l][g]=0;
		maxhealth[l][g]=0;*/
		//System.out.println(health[l][g-1]+"/"+maxhealth[l][g-1]+" �����");
	}
	public void vniz(){
		gameX.animation++;
		gameX.anim[gameX.animation][0]=0;
		gameX.anim[gameX.animation][1]=3;
		gameX.anim[gameX.animation][2]=l+g*15;
		lol[l][g+1]="7";
		lol[l][g]="3";
		health[l][g+1]=health[l][g];
		maxhealth[l][g+1]=maxhealth[l][g];
		damage[l][g+1]=damage[l][g];
		armor[l][g+1]=armor[l][g];
		weapon[l][g+1]=weapon[l][g];
		/*health[l][g]=0;
		damage[l][g]=0;
		armor[l][g]=0;
		weapon[l][g]=0;
		maxhealth[l][g]=0;*/
		//System.out.println(health[l][g+1]+"/"+maxhealth[l][g+1]+" ����");
	}
	public void napravo(){
		gameX.animation++;
		gameX.anim[gameX.animation][0]=0;
		gameX.anim[gameX.animation][1]=1;
		gameX.anim[gameX.animation][2]=l+g*15;
		lol[l+1][g]="7";
		lol[l][g]="3";
		health[l+1][g]=health[l][g];
		maxhealth[l+1][g]=maxhealth[l][g];
		damage[l+1][g]=damage[l][g];
		armor[l+1][g]=armor[l][g];
		weapon[l+1][g]=weapon[l][g];
		/*health[l][g]=0;
		damage[l][g]=0;
		armor[l][g]=0;
		weapon[l][g]=0;
		maxhealth[l][g]=0;*/
		//System.out.println(health[l+1][g]+"/"+maxhealth[l+1][g]+" �������");
		
	}
	public void nalevo(){
		gameX.animation++;
		gameX.anim[gameX.animation][0]=0;
		gameX.anim[gameX.animation][1]=0;
		gameX.anim[gameX.animation][2]=l+g*15;
		lol[l-1][g]="7";
		lol[l][g]="3";
		health[l-1][g]=health[l][g];
		maxhealth[l-1][g]=maxhealth[l][g];
		damage[l-1][g]=damage[l][g];
		armor[l-1][g]=armor[l][g];
		weapon[l-1][g]=weapon[l][g];
		/*health[l][g]=0;
		damage[l][g]=0;
		armor[l][g]=0;
		weapon[l][g]=0;
		maxhealth[l][g]=0;*/
		//System.out.println(health[l-1][g]+"/"+maxhealth[l-1][g]+" ������");
	}
	public void profreader(){
		exp=Character.getNumericValue(profile.charAt(2))*10+Character.getNumericValue(profile.charAt(3));
		sword=Character.getNumericValue(profile.charAt(4))*10+Character.getNumericValue(profile.charAt(5));
		gameX.sword=sword;
		dual=Character.getNumericValue(profile.charAt(6))*10+Character.getNumericValue(profile.charAt(7));
		gameX.dual=dual;
		bow=Character.getNumericValue(profile.charAt(8))*10+Character.getNumericValue(profile.charAt(9));
		gameX.bow=bow;
		magic=Character.getNumericValue(profile.charAt(10))*10+Character.getNumericValue(profile.charAt(11));
		gameX.magic=magic;
		koja=Character.getNumericValue(profile.charAt(12))*10+Character.getNumericValue(profile.charAt(13));
		gameX.koja=koja;
		iron=Character.getNumericValue(profile.charAt(14))*10+Character.getNumericValue(profile.charAt(15));
		gameX.iron=iron;
		gold=Character.getNumericValue(profile.charAt(16))*10+Character.getNumericValue(profile.charAt(17));
		gameX.gold=gold;
		inventory[0][0]=Character.getNumericValue(profile.charAt(18))*10+Character.getNumericValue(profile.charAt(19));
		inventory[1][0]=Character.getNumericValue(profile.charAt(20))*10+Character.getNumericValue(profile.charAt(21));
		inventory[2][0]=Character.getNumericValue(profile.charAt(22))*10+Character.getNumericValue(profile.charAt(23));
		inventory[3][0]=Character.getNumericValue(profile.charAt(24))*10+Character.getNumericValue(profile.charAt(25));
		inventory[4][0]=Character.getNumericValue(profile.charAt(26))*10+Character.getNumericValue(profile.charAt(27));
		inventory[0][1]=Character.getNumericValue(profile.charAt(28))*10+Character.getNumericValue(profile.charAt(29));
		inventory[1][1]=Character.getNumericValue(profile.charAt(30))*10+Character.getNumericValue(profile.charAt(31));
		inventory[2][1]=Character.getNumericValue(profile.charAt(32))*10+Character.getNumericValue(profile.charAt(33));
		inventory[3][1]=Character.getNumericValue(profile.charAt(34))*10+Character.getNumericValue(profile.charAt(35));
		inventory[4][1]=Character.getNumericValue(profile.charAt(36))*10+Character.getNumericValue(profile.charAt(37));
		inventory[0][2]=Character.getNumericValue(profile.charAt(38))*10+Character.getNumericValue(profile.charAt(39));
		inventory[1][2]=Character.getNumericValue(profile.charAt(40))*10+Character.getNumericValue(profile.charAt(41));
		inventory[2][2]=Character.getNumericValue(profile.charAt(42))*10+Character.getNumericValue(profile.charAt(43));
		inventory[3][2]=Character.getNumericValue(profile.charAt(44))*10+Character.getNumericValue(profile.charAt(45));
		inventory[4][2]=Character.getNumericValue(profile.charAt(46))*10+Character.getNumericValue(profile.charAt(47));
		inventory[0][3]=Character.getNumericValue(profile.charAt(48))*10+Character.getNumericValue(profile.charAt(49));
		inventory[1][3]=Character.getNumericValue(profile.charAt(50))*10+Character.getNumericValue(profile.charAt(51));
		inventory[2][3]=Character.getNumericValue(profile.charAt(52))*10+Character.getNumericValue(profile.charAt(53));
		inventory[3][3]=Character.getNumericValue(profile.charAt(54))*10+Character.getNumericValue(profile.charAt(55));
		inventory[4][3]=Character.getNumericValue(profile.charAt(56))*10+Character.getNumericValue(profile.charAt(57));
		inventory[0][4]=Character.getNumericValue(profile.charAt(58))*10+Character.getNumericValue(profile.charAt(59));
		inventory[1][4]=Character.getNumericValue(profile.charAt(60))*10+Character.getNumericValue(profile.charAt(61));
		inventory[2][4]=Character.getNumericValue(profile.charAt(62))*10+Character.getNumericValue(profile.charAt(63));
		inventory[3][4]=Character.getNumericValue(profile.charAt(64))*10+Character.getNumericValue(profile.charAt(65));
		inventory[4][4]=Character.getNumericValue(profile.charAt(66))*10+Character.getNumericValue(profile.charAt(67));
		inventory[0][5]=Character.getNumericValue(profile.charAt(68))*10+Character.getNumericValue(profile.charAt(69));
		inventory[1][5]=Character.getNumericValue(profile.charAt(70))*10+Character.getNumericValue(profile.charAt(71));
		inventory[2][5]=Character.getNumericValue(profile.charAt(72))*10+Character.getNumericValue(profile.charAt(73));
		inventory[3][5]=Character.getNumericValue(profile.charAt(74))*10+Character.getNumericValue(profile.charAt(75));
		inventory[4][5]=Character.getNumericValue(profile.charAt(76))*10+Character.getNumericValue(profile.charAt(77));
		eqweapon=Character.getNumericValue(profile.charAt(78))*10+Character.getNumericValue(profile.charAt(79));
		gameX.eqweapon=eqweapon;
		eqarmor=Character.getNumericValue(profile.charAt(80))*10+Character.getNumericValue(profile.charAt(81));
		gameX.eqarmor=eqarmor;
		damage();
		CampFinished=Character.getNumericValue(profile.charAt(82));
		gameX.CampFinished=CampFinished;
		if(eqarmor<=4){
			mhp=100+koja*3;
			hp=mhp;
			gameX.mhp=mhp;
			gameX.hp=hp;
		}
		else if(eqarmor<=9){
			mhp=100+iron*5;
			hp=mhp;
			gameX.mhp=mhp;
			gameX.hp=hp;
		}
		else{
			mhp=100+gold*3;
			hp=mhp;
			gameX.mhp=mhp;
			gameX.hp=hp;
			mmp=100+gold*3;
			mp=mmp;
			gameX.mmp=mmp;
			gameX.mp=mp;
		}
	}
	public void profwriter(){
		File ololo = new File("./Pasan_Campaign/profile.pasan");
		if (ololo.exists()) {
		    try {
		        ololo.delete();
		        ololo.createNewFile();
		        PrintWriter out = new PrintWriter(ololo.getAbsoluteFile());
		        try{
		        	profile="";
		        	profile+=Integer.parseInt(level)/10;
		        	profile+=Integer.parseInt(level)-Integer.parseInt(level)/10*10;
		        	profile+=exp/10;
		        	profile+=exp-exp/10*10;
		        	profile+=sword/10;
		        	profile+=sword-sword/10*10;
		        	profile+=dual/10;
		        	profile+=dual-dual/10*10;
		        	profile+=bow/10;
		        	profile+=bow-bow/10*10;
		        	profile+=magic/10;
		        	profile+=magic-magic/10*10;
		        	profile+=koja/10;
		        	profile+=koja-koja/10*10;
		        	profile+=iron/10;
		        	profile+=iron-iron/10*10;
		        	profile+=gold/10;
		        	profile+=gold-gold/10*10;
		        	profile+=inventory[0][0]/10;
		        	profile+=inventory[0][0]-inventory[0][0]/10*10;
		        	profile+=inventory[1][0]/10;
		        	profile+=inventory[1][0]-inventory[1][0]/10*10;
		        	profile+=inventory[2][0]/10;
		        	profile+=inventory[2][0]-inventory[2][0]/10*10;
		        	profile+=inventory[3][0]/10;
		        	profile+=inventory[3][0]-inventory[3][0]/10*10;
		        	profile+=inventory[4][0]/10;
		        	profile+=inventory[4][0]-inventory[4][0]/10*10;
		        	profile+=inventory[0][1]/10;
		        	profile+=inventory[0][1]-inventory[0][1]/10*10;
		        	profile+=inventory[1][1]/10;
		        	profile+=inventory[1][1]-inventory[1][1]/10*10;
		        	profile+=inventory[2][1]/10;
		        	profile+=inventory[2][1]-inventory[2][1]/10*10;
		        	profile+=inventory[3][1]/10;
		        	profile+=inventory[3][1]-inventory[3][1]/10*10;
		        	profile+=inventory[4][1]/10;
		        	profile+=inventory[4][1]-inventory[4][1]/10*10;
		        	profile+=inventory[0][2]/10;
		        	profile+=inventory[0][2]-inventory[0][2]/10*10;
		        	profile+=inventory[1][2]/10;
		        	profile+=inventory[1][2]-inventory[1][2]/10*10;
		        	profile+=inventory[2][2]/10;
		        	profile+=inventory[2][2]-inventory[2][2]/10*10;
		        	profile+=inventory[3][2]/10;
		        	profile+=inventory[3][2]-inventory[3][2]/10*10;
		        	profile+=inventory[4][2]/10;
		        	profile+=inventory[4][2]-inventory[4][2]/10*10;
		        	profile+=inventory[0][3]/10;
		        	profile+=inventory[0][3]-inventory[0][3]/10*10;
		        	profile+=inventory[1][3]/10;
		        	profile+=inventory[1][3]-inventory[1][3]/10*10;
		        	profile+=inventory[2][3]/10;
		        	profile+=inventory[2][3]-inventory[2][3]/10*10;
		        	profile+=inventory[3][3]/10;
		        	profile+=inventory[3][3]-inventory[3][3]/10*10;
		        	profile+=inventory[4][3]/10;
		        	profile+=inventory[4][3]-inventory[4][3]/10*10;
		        	profile+=inventory[0][4]/10;
		        	profile+=inventory[0][4]-inventory[0][4]/10*10;
		        	profile+=inventory[1][4]/10;
		        	profile+=inventory[1][4]-inventory[1][4]/10*10;
		        	profile+=inventory[2][4]/10;
		        	profile+=inventory[2][4]-inventory[2][4]/10*10;
		        	profile+=inventory[3][4]/10;
		        	profile+=inventory[3][4]-inventory[3][4]/10*10;
		        	profile+=inventory[4][4]/10;
		        	profile+=inventory[4][4]-inventory[4][4]/10*10;
		        	profile+=inventory[0][5]/10;
		        	profile+=inventory[0][5]-inventory[0][5]/10*10;
		        	profile+=inventory[1][5]/10;
		        	profile+=inventory[1][5]-inventory[1][5]/10*10;
		        	profile+=inventory[2][5]/10;
		        	profile+=inventory[2][5]-inventory[2][5]/10*10;
		        	profile+=inventory[3][5]/10;
		        	profile+=inventory[3][5]-inventory[3][5]/10*10;
		        	profile+=inventory[4][5]/10;
		        	profile+=inventory[4][5]-inventory[4][5]/10*10;
		        	profile+=eqweapon/10;
		        	profile+=eqweapon-eqweapon/10*10;
		        	profile+=eqarmor/10;
		        	profile+=eqarmor-eqarmor/10*10;
		        	profile+=CampFinished;
		            out.print(profile);
		        } finally {
		            out.close();
		        }
		   } catch (IOException ex) {
		        System.out.println("������, ���");
		    }
		}

	}
	public void chest(){
		
			rnd=(int)(Math.random()*200);
			if(rnd<=15){
				if(inventory[0][0]<99){
				inventory[0][0]+=1;
				gameX.inv[0][0]=inventory[0][0];
				}
				if((inventory[0][0]>=2)&(inventory[0][1]==0)){
					chestMesDual("������ ��� (������� I)");
				}
				else{
					chestMes("������ ��� (������� I)");
				}
			}
			else if(rnd<=30){
				if(inventory[0][2]<99){
				inventory[0][2]+=1;
				gameX.inv[0][2]=inventory[0][2];
				}
				chestMes("�������� ��� (������� I)");
			}
			else if(rnd<=45){
				if(inventory[0][3]<99){
				inventory[0][3]+=1;
				gameX.inv[0][3]=inventory[0][3];
				}
				chestMes("��������� ������� ����� (������� I)");
			}
			else if(rnd<=60){
				if(inventory[0][4]<99){
				inventory[0][4]+=1;
				gameX.inv[0][4]=inventory[0][4];
				}
				chestMes("������ �������� ����� (������� I)");
			}
			else if(rnd<=75){
				if(inventory[0][5]<99){
				inventory[0][5]+=1;
				gameX.inv[0][5]=inventory[0][5];
				}
				chestMes("���������� ������� ����� (������� I)");
			}
			else if(rnd<=89){
				if(inventory[1][0]<99){
				inventory[1][0]+=1;
				gameX.inv[1][0]=inventory[1][0];
				}
				if((inventory[1][0]>=2)&(inventory[1][1]==0)){
					chestMesDual("�������� ��� (������� II)");
				}
				else{
					chestMes("�������� ��� (������� II)");
				}
			}
			else if(rnd<=103){
				if(inventory[1][2]<99){
				inventory[1][2]+=1;
				gameX.inv[1][2]=inventory[1][2];
				}
				chestMes("��������� ��� (������� II)");
			}
			else if(rnd<=121){
				if(inventory[1][3]<99){
				inventory[1][3]+=1;
				gameX.inv[1][3]=inventory[1][3];
				}
				chestMes("���������� ������� ����� (������� II)");
			}
			else if(rnd<=135){
				if(inventory[1][4]<99){
				inventory[1][4]+=1;
				gameX.inv[1][4]=inventory[1][4];
				}
				chestMes("�������� �������� ����� (������� II)");
			}
			else if(rnd<=149){
				if(inventory[1][5]<99){
				inventory[1][5]+=1;
				gameX.inv[1][5]=inventory[1][5];
				}
				chestMes("������� ������� ����� (������� II)");
			}
			else if(rnd<=155){
				if(inventory[2][0]<99){
				inventory[2][0]+=1;
				gameX.inv[2][0]=inventory[2][0];
				}
				if((inventory[2][0]>=2)&(inventory[2][1]==0)){
					chestMesDual("����������� ��� (������� III)");
				}
				else{
					chestMes("����������� ��� (������� III)");
				}
			}
			else if(rnd<=161){
				if(inventory[2][2]<99){
				inventory[2][2]+=1;
				gameX.inv[2][2]=inventory[2][2];
				}
				chestMes("��������� ��� (������� III)");
			}
			else if(rnd<=167){
				if(inventory[2][3]<99){
				inventory[2][3]+=1;
				gameX.inv[2][3]=inventory[2][3];
				}
				chestMes("������� ������� ����� (������� III)");
			}
			else if(rnd<=173){
				if(inventory[2][4]<99){
				inventory[2][4]+=1;
				gameX.inv[2][4]=inventory[2][4];
				}
				chestMes("����������� �������� ����� (������� III)");
			}
			else if(rnd<=179){
				if(inventory[2][5]<99){
				inventory[2][5]+=1;
				gameX.inv[2][5]=inventory[2][5];
				}
				chestMes("�������� ������� ����� (������� III)");
			}
			else if(rnd<=182){
				if(inventory[3][0]<99){
				inventory[3][0]+=1;
				gameX.inv[3][0]=inventory[3][0];
				}
				if((inventory[3][0]>=2)&(inventory[3][1]==0)){
					chestMesDual("��� ��������� ������ (������� IV)");
				}
				else{
					chestMes("��� ��������� ������ (������� IV)");
				}
			}
			else if(rnd<=185){
				if(inventory[3][2]<99){
				inventory[3][2]+=1;
				gameX.inv[3][2]=inventory[3][2];
				}
				chestMes("������������ ��� (������� IV)");
			}
			else if(rnd<=188){
				if(inventory[3][3]<99){
				inventory[3][3]+=1;
				gameX.inv[3][3]=inventory[3][3];
				}
				chestMes("��������� ������� ����� (������� IV)");
			}
			else if(rnd<=191){
				if(inventory[3][4]<99){
				inventory[3][4]+=1;
				gameX.inv[3][4]=inventory[3][4];
				}
				chestMes("�������� ������ �������� (������� IV)");
			}
			else if(rnd<=194){
				if(inventory[3][5]<99){
				inventory[3][5]+=1;
				gameX.inv[3][5]=inventory[3][5];
				}
				chestMes("��������� ������� ������ (������� IV)");
			}
			else if(rnd<=195){
				if(inventory[4][0]<99){
				inventory[4][0]+=1;
				gameX.inv[4][0]=inventory[4][0];
				}
				if((inventory[4][0]>=2)&(inventory[4][1]==0)){
					chestMesDual("����������� ��� ������������ ����� (������� V)");
				}
				else{
					chestMes("����������� ��� ������������ ����� (������� V)");
				}
			}
			else if(rnd<=196){
				if(inventory[4][2]<99){
				inventory[4][2]+=1;
				gameX.inv[4][2]=inventory[4][2];
				}
				chestMes("����������� ��� ������������ �������� (������� V)");
			}
			else if(rnd<=197){
				if(inventory[4][3]<99){
				inventory[4][3]+=1;
				gameX.inv[4][3]=inventory[4][3];
				}
				chestMes("������� ������ �������� ��������� (������� V)");
			}
			else if(rnd<=198){
				if(inventory[4][4]<99){
				inventory[4][4]+=1;
				gameX.inv[4][4]=inventory[4][4];
				}
				chestMes("���� ������������ ������ (������� V)");
			}
			else{
				if(inventory[4][5]<99){
				inventory[4][5]+=1;
				gameX.inv[4][5]=inventory[4][5];
				}
				chestMes("������� ���� �������� (������� V)");
			}
		
	}
	public void damage(){
		k=100;
		if(eqweapon==0){dmg=8;}
		else if(eqweapon==1){dmg=14;}
		else if(eqweapon==2){dmg=20;}
		else if(eqweapon==3){dmg=16;}
		else if(eqweapon==4){dmg=36;}
		else if(eqweapon==5){dmg=12;}
		else if(eqweapon==6){dmg=21;}
		else if(eqweapon==7){dmg=30;}
		else if(eqweapon==8){dmg=24;}
		else if(eqweapon==9){dmg=48;}
		else if(eqweapon==10){dmg=6;}
		else if(eqweapon==11){dmg=10;}
		else if(eqweapon==12){dmg=18;}
		else if(eqweapon==13){dmg=16;}
		else if(eqweapon==14){dmg=28;}
		if(eqweapon<=4){
			k+=sword*5;
		}
		else if(eqweapon<=9){
			k+=dual*5;
		}
		else if(eqweapon<=14){
			k+=bow*5;
		}
		dmg=dmg*k/100;
		if(eqarmor==8){
			dmg=dmg*125/100;
		}
		else if(eqarmor==4){
			dmg=dmg*120/100;
		}
		mdmg=0;
		k=100;
		if(magic>=10){
			mdmg+=10+10*magic*5/100;
		}
		if(eqweapon==13){
			mdmg+=magic/2+magic/2*magic*5/100;
		}
		if(eqarmor==14){
			k+=30;
		}
		if((gold>=20)&(eqarmor>=10)){
			k+=30;
		}
		mdmg=mdmg*k/100;
		dmg+=mdmg;
	}
	public void blockmelee(){
		k=0;
		if(eqarmor==0){
			k=5;
		}
		else if(eqarmor==1){
			k=10;
		}
		else if(eqarmor==2){
			k=20;
		}
		else if(eqarmor==3){
			k=35;
		}
		else if(eqarmor==4){
			k=50;
		}
		else if(eqarmor==5){
			k=10;
		}
		else if(eqarmor==6){
			k=20;
		}
		else if(eqarmor==7){
			k=35;
		}
		else if(eqarmor==8){
			k=40;
		}
		else if(eqarmor==9){
			k=80;
		}
		else if(eqarmor==10){
			k=6;
		}
		else if(eqarmor==11){
			k=15;
		}
		else if(eqarmor==12){
			k=25;
		}
		else if(eqarmor==13){
			k=30;
		}
		else if(eqarmor==14){
			k=60;
		}
		if(eqweapon==3){
			k+=(100-k)*3/10;
		}
		else if(eqweapon==8){
			k+=(100-k)/4;
		}
		if((eqarmor>=5)&(eqarmor<=9)&(iron>=10)){
			k+=(100-k)/4;
		}
		if((weapon[l][g]==1)&(sword>=10)){
			k+=(100-k)/4;
		}
		if((weapon[l][g]==2)&(dual>=10)){
			k+=(100-k)/4;
		}
	}
	public void blockrange(){
		k=0;
		if(eqarmor==0){
			k=5;
		}
		else if(eqarmor==1){
			k=10;
		}
		else if(eqarmor==2){
			k=20;
		}
		else if(eqarmor==3){
			k=35;
		}
		else if(eqarmor==4){
			k=50;
		}
		else if(eqarmor==5){
			k=10;
		}
		else if(eqarmor==6){
			k=20;
		}
		else if(eqarmor==7){
			k=35;
		}
		else if(eqarmor==8){
			k=40;
		}
		else if(eqarmor==9){
			k=80;
		}
		else if(eqarmor==10){
			k=6;
		}
		else if(eqarmor==11){
			k=15;
		}
		else if(eqarmor==12){
			k=25;
		}
		else if(eqarmor==13){
			k=30;
		}
		else if(eqarmor==14){
			k=60;
		}
		if((eqarmor>=5)&(eqarmor<=9)&(iron>=10)){
			k+=(100-k)/4;
		}
	}
	public void evasionmelee(){
		k=0;
		if(eqarmor==3){
			k+=20;
		}
		else if(eqarmor==4){
			k+=30;
		}
		if((eqarmor<=4)&(koja>=10)){
			k+=15;
		}
		if(eqarmor<=4){
			k+=koja;
		}
	}
	public void evasionrange(){
		k=0;
		
		if(bow>=10){
			k+=35;
		}
	}
	public void bladeflurry(){
		
		if((eqweapon>=5)&(eqweapon<=9)&(dual>=30)){
			if(hero/15>=1){
				if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15-1])==5){
					health[hero-(hero/15*15)][hero/15-1]-=dmg/5;
					gameX.health[hero-(hero/15*15)][hero/15-1]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)][hero/15-1]==0)|(armor[hero-(hero/15*15)][hero/15-1]==1))){
						health[hero-(hero/15*15)][hero/15-1]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)][hero/15-1]==2)|(armor[hero-(hero/15*15)][hero/15-1]==3))){
						health[hero-(hero/15*15)][hero/15-1]-=dmg*4/10/5;
						gameX.health=health;
					}
					isDead(hero-15);
				}
			}
			if(hero/15<=8){
				if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15+1])==5){
					health[hero-(hero/15*15)][hero/15+1]-=dmg/5;
					gameX.health[hero-(hero/15*15)][hero/15+1]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)][hero/15+1]==0)|(armor[hero-(hero/15*15)][hero/15+1]==1))){
						health[hero-(hero/15*15)][hero/15+1]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)][hero/15+1]==2)|(armor[hero-(hero/15*15)][hero/15+1]==3))){
						health[hero-(hero/15*15)][hero/15+1]-=dmg*4/10/5;
						gameX.health=health;
					}
					isDead(hero+15);
				}
			}
			if(hero-hero/15*15>=1){
				if(Integer.parseInt(lol[hero-(hero/15*15)-1][hero/15])==5){
					health[hero-(hero/15*15)-1][hero/15]-=dmg/5;
					gameX.health[hero-(hero/15*15)-1][hero/15]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)-1][hero/15]==0)|(armor[hero-(hero/15*15)-1][hero/15]==1))){
						health[hero-(hero/15*15)-1][hero/15]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)-1][hero/15]==2)|(armor[hero-(hero/15*15)-1][hero/15]==3))){
						health[hero-(hero/15*15)-1][hero/15]-=dmg*4/10/5;
						gameX.health=health;
					}
				
					isDead(hero-1);
				}
			}
			if(hero-hero/15*15<=13){
				if(Integer.parseInt(lol[hero-(hero/15*15)+1][hero/15])==5){
					health[hero-(hero/15*15)+1][hero/15]-=dmg/5;
					gameX.health[hero-(hero/15*15)+1][hero/15]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)+1][hero/15]==0)|(armor[hero-(hero/15*15)+1][hero/15]==1))){
						health[hero-(hero/15*15)+1][hero/15]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)+1][hero/15]==2)|(armor[hero-(hero/15*15)+1][hero/15]==3))){
						health[hero-(hero/15*15)-1][hero/15]-=dmg*4/10/5;
						gameX.health=health;
					}
					
					isDead(hero+1);
				}
			}
		}
		if(eqweapon==9){
			if(hero/15>=1){
				if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15-1])==5){
					health[hero-(hero/15*15)][hero/15-1]-=dmg/5;
					gameX.health[hero-(hero/15*15)][hero/15-1]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)][hero/15-1]==0)|(armor[hero-(hero/15*15)][hero/15-1]==1))){
						health[hero-(hero/15*15)][hero/15-1]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)][hero/15-1]==2)|(armor[hero-(hero/15*15)][hero/15-1]==3))){
						health[hero-(hero/15*15)][hero/15-1]-=dmg*4/10/5;
						gameX.health=health;
					}
					
					isDead(hero-15);
				}
			}
			if(hero/15<=8){
				if(Integer.parseInt(lol[hero-(hero/15*15)][hero/15+1])==5){
					health[hero-(hero/15*15)][hero/15+1]-=dmg/5;
					gameX.health[hero-(hero/15*15)][hero/15+1]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)][hero/15+1]==0)|(armor[hero-(hero/15*15)][hero/15+1]==1))){
						health[hero-(hero/15*15)][hero/15+1]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)][hero/15+1]==2)|(armor[hero-(hero/15*15)][hero/15+1]==3))){
						health[hero-(hero/15*15)][hero/15+1]-=dmg*4/10/5;
						gameX.health=health;
					}
					
					isDead(hero+15);
				}
			}
			if(hero-hero/15*15>=1){
				if(Integer.parseInt(lol[hero-(hero/15*15)-1][hero/15])==5){
					health[hero-(hero/15*15)-1][hero/15]-=dmg/5;
					gameX.health[hero-(hero/15*15)-1][hero/15]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)-1][hero/15]==0)|(armor[hero-(hero/15*15)-1][hero/15]==1))){
						health[hero-(hero/15*15)-1][hero/15]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)-1][hero/15]==2)|(armor[hero-(hero/15*15)-1][hero/15]==3))){
						health[hero-(hero/15*15)-1][hero/15]-=dmg*4/10/5;
						gameX.health=health;
					}
					
					isDead(hero-1);
				}
			}
			if(hero-hero/15*15<=13){
				if(Integer.parseInt(lol[hero-(hero/15*15)+1][hero/15])==5){
					health[hero-(hero/15*15)+1][hero/15]-=dmg/5;
					gameX.health[hero-(hero/15*15)+1][hero/15]-=dmg/5;
					if((koja>=20)&((armor[hero-(hero/15*15)+1][hero/15]==0)|(armor[hero-(hero/15*15)+1][hero/15]==1))){
						health[hero-(hero/15*15)+1][hero/15]-=dmg/2/5;
						gameX.health=health;
					}
					if((iron>=20)&((armor[hero-(hero/15*15)+1][hero/15]==2)|(armor[hero-(hero/15*15)+1][hero/15]==3))){
						health[hero-(hero/15*15)-1][hero/15]-=dmg*4/10/5;
						gameX.health=health;
					}
				
					isDead(hero+1);
				}
			}
		}
	}
	public void cold_embrace(){
		gameX.hero=hero;
		manacost=45;
		if (eqarmor==13){
			manacost=manacost*125/100;
		}
		if((gold>=10)&(eqarmor>=10)){
			manacost=manacost*75/100;
		}
		if(mp>=manacost){
			mp-=manacost;
			gameX.mp=mp;
			gameX.animation+=1;
			gameX.anim[gameX.animation][0]=3;
			spd=10+10*magic*5/100;
			if((eqarmor>=10)&(gold>=20)){
				spd+=spd*30/100;
			}
			if(eqarmor==14){
				spd+=spd*30/100;
			}
		if(hero/15>=1){
			if(Integer.parseInt(lol[hero-hero/15*15][hero/15-1])==5){
				lol[hero-hero/15*15][hero/15-1]="7";
				hp+=spd/2;
				if(hp>mhp){
					hp=mhp;
				}
				gameX.hp=hp;
				gameX.anim[gameX.animation][1]=hero-15;
				health[hero-hero/15*15][hero/15-1]-=spd;
				gameX.health[hero-hero/15*15][hero/15-1]-=spd;
				isDead(hero-15);
			}
		}
		if(hero-hero/15*15>=1){
			if(Integer.parseInt(lol[hero-hero/15*15-1][hero/15])==5){
				lol[hero-hero/15*15-1][hero/15]="7";
				hp+=spd/2;
				if(hp>mhp){
					hp=mhp;
				}
				gameX.hp=hp;
				gameX.anim[gameX.animation][2]=hero-1;
				health[hero-hero/15*15-1][hero/15]-=spd;
				gameX.health[hero-hero/15*15-1][hero/15]-=spd;
				isDead(hero-1);
			}
		}
		if(hero/15<=8){
			if(Integer.parseInt(lol[hero-hero/15*15][hero/15+1])==5){
				lol[hero-hero/15*15][hero/15+1]="7";
				hp+=spd/2;
				if(hp>mhp){
					hp=mhp;
				}
				gameX.hp=hp;
				gameX.anim[gameX.animation][3]=hero+15;
				health[hero-hero/15*15][hero/15+1]-=spd;
				gameX.health[hero-hero/15*15][hero/15+1]-=spd;
				isDead(hero+15);
			}
		}
		if(hero-hero/15*15<=13){
			
			if(Integer.parseInt(lol[hero-hero/15*15+1][hero/15])==5){
				lol[hero-hero/15*15+1][hero/15]="7";
				hp+=spd/2;
				if(hp>mhp){
					hp=mhp;
				}
				gameX.hp=hp;
				gameX.anim[gameX.animation][4]=hero+1;
				health[hero-hero/15*15+1][hero/15]-=spd;
				gameX.health[hero-hero/15*15+1][hero/15]-=spd;
				isDead(hero+1);
			}
		}
		enemies();
		gameX.musicThread.cold_embrace();
		}
		
	}
	public void isDead(int v){
		if(health[v-v/15*15][v/15]<=0){
			lol[v-v/15*15][v/15]="3";
			gameX.lol[v-v/15*15][v/15]="3";
			if(exp<99){
				exp+=1;
			}
			if((gold>=30)&(eqarmor>=10)){
				mp+=mmp*30/100;
				if(mp>mmp){
					mp=mmp;
				}
				gameX.mp=mp;
				gameX.mmp=mmp;
			}
		}
		gameX.exp=exp;
	}
	public void storm(){
		manacost=90;
		if (eqarmor==13){
			manacost=manacost*125/100;
		}
		if((gold>=10)&(eqarmor>=10)){
			manacost=manacost*75/100;
		}
		if(mp>manacost){
			mp-=manacost;
			gameX.mp=mp;
			gameX.hero=hero;
			spd=20+20*magic*5/100;
			if((eqarmor>=10)&(gold>=20)){
				spd+=spd*30/100;
			}
			if(eqarmor==14){
				spd+=spd*30/100;
			}
			gameX.animation+=1;
			gameX.anim[gameX.animation][0]=4;

			if (hero-(hero/15*15)>=2){minl=hero-(hero/15*15)-2;}
			else {minl=0;}
			if (hero-(hero/15*15)<=12){maxl=hero-(hero/15*15)+2;}
			else {maxl=14;}
			if (hero/15>=2){ming=hero/15-2;}
			else {ming=0;}
			if (hero/15<=7){maxg=hero/15+2;}
			else {maxg=9;}
			
			gameX.anim[gameX.animation][1]=minl;
			gameX.anim[gameX.animation][2]=maxl;
			gameX.anim[gameX.animation][3]=ming;
			gameX.anim[gameX.animation][4]=maxg;
			for (l=minl; l<=maxl; l++){
				for(g=ming; g<=maxg; g++){
					if(Integer.parseInt(lol[l][g])==5){
						health[l][g]-=spd;
						gameX.health[l][g]-=spd;
						isDead(l+g*15);
					}
				}
			}
			enemies();
			gameX.musicThread.storm();
		}
		
	}
	public void level_finished()
	{
		if (Camp==true){
			level=Integer.toString(Integer.parseInt(level)+1);
		}
		profwriter();
		gameX.inMessage=4;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("������� �������!");
		gameX.mes.setText("<html>������� �������!</html>");
	}
	public void deathstrike(int b){
		if(eqweapon==4){
			rnd=(int)(Math.random()*100);
			if(rnd<=4){
				health[b-(b/15*15)][b/15]=0;
				gameX.health[b-(b/15*15)][b/15]=0;
				gameX.animation+=1;
				gameX.anim[gameX.animation][0]=7;
				gameX.anim[gameX.animation][0]=b-b/15*15;
				gameX.anim[gameX.animation][1]=b/15;
				isDead(b);
			}
		}
	}
	public void mlvl(){
		gameX.inMessage=3;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("��������� ������");
		gameX.mes.setText("<html>��������� ������������ ������� ������.</html>");
	}
	public void ned(){
		gameX.inMessage=3;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("��������� ������");
		gameX.mes.setText("<html>������������ ����� �������.</html>");
	}
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	public void spacebar(){
		if(menu==1){
			if (inv_ram==0){
				invMes("������ ���<br>����:8", 0, 0);
			}
			else if (inv_ram==1){
				invMes("�������� ���<br>����:12", 1, 0);
			}
			else if(inv_ram==2){
				invMes("����������� ���<br>����:20", 2, 0);
			}
			else if(inv_ram==3){
				invMes("��� ��������� ������<br>����:16<br>��������� 30% ����� � ������� ���", 3, 0);
			}
			else if(inv_ram==4){
				invMes("����������� ��� ������������ �����<br>����:36<br>������ ����� � ������������ 5% ��������� ������� ����", 4, 0);
			}
			else if(inv_ram==5){
				invMes("������ ������ ������<br>����:12", 0, 1);
			}
			else if(inv_ram==6){
				invMes("�������� ������ ������<br>����:21", 1, 1);
			}
			else if(inv_ram==7){
				invMes("����������� ������ ������<br>����:30", 2, 1);
			}
			else if(inv_ram==8){
				invMes("������ ������ �������� ������������<br>����:24<br>��������� 25% ����� � ������� ���", 3, 1);
			}
			else if(inv_ram==9){
				invMes("����������� ������ ������ ����� ������<br>����:48<br>��� ������ ����� ������������� ������� 20% ����� ����������� �� 4-� �������� �������", 4, 1);
			}
			else if(inv_ram==10){
				invMes("�������� ���<br>����:6<br>��������� �����: 2 ������", 0, 2);
			}
			else if(inv_ram==11){
				invMes("��������� ���<br>����:10<br>��������� �����: 3 ������", 1, 2);
			}
			else if(inv_ram==12){
				invMes("��������� ���<br>����:18<br>��������� �����: 3 ������", 2, 2);
			}
			else if(inv_ram==13){
				invMes("������������ ���<br>����:16<br>��������� �����: 3 ������<br>���� ����� ������������� ������� ���� � ������� 50% �� ������ �����", 3, 2);
			}
			else if(inv_ram==14){
				invMes("����������� ��� ������������ ��������<br>����:28<br>��������� �����: 4 ������", 4, 2);
			}
			else if(inv_ram==15){
				invMes("��������� ������� �����<br>��������� 5% �����", 0, 3);
			}
			else if(inv_ram==16){
				invMes("���������� ������� �����<br>��������� 10% �����", 1, 3);
			}
			else if(inv_ram==17){
				invMes("������� ������� �����<br>��������� 20% �����", 2, 3);
			}
			else if(inv_ram==18){
				invMes("��������� ������� �����<br>��������� 35% �����<br>+20% ���� ��������� �� ����� � ������� ���", 3, 3);
			}
			else if(inv_ram==19){
				invMes("������� ������ �������� ���������<br>��������� 50% �����<br>+35% ���� ��������� �� ����� � ������� ���<br>���� ������ ������� �� 20% ������ �����", 4, 3);
			}
			else if(inv_ram==20){
				invMes("������ �������� �����<br>��������� 10% �����", 0, 4);
			}
			else if(inv_ram==21){
				invMes("�������� �������� �����<br>��������� 20% �����", 1, 4);
			}
			else if(inv_ram==22){
				invMes("����������� �������� �����<br>��������� 35% �����", 2, 4);
			}
			else if(inv_ram==23){
				invMes("�������� ������ ��������<br>��������� 40% �����<br>���� ������ ������� �� 25% ������ �����", 3, 4);
			}
			else if(inv_ram==24){
				invMes("���� ������������ ������<br>��������� 80% �����", 4, 4);
			}
			else if(inv_ram==25){
				invMes("���������� ������� �����<br>��������� 6% �����", 0, 5);
			}
			else if(inv_ram==26){
				invMes("������� ������� �����<br>��������� 15% �����", 1, 5);
			}
			else if(inv_ram==27){
				invMes("�������� ������� �����<br>��������� 25% �����", 2, 5);
			}
			else if(inv_ram==28){
				invMes("��������� ������� ������<br>��������� 30% �����<br>����������, ��������� ��� � ������� ���, �������� ����,������ ������ ������ ������� �����<br>���� ���������� ����� �� 25% ������ ����", 3, 5);
			}
			else if(inv_ram==29){
				invMes("������� ���� ��������<br>��������� 60% �����<br>���� ���������� ������� �� 35% ������ �����", 4, 5);
			}
			
		}
		else if (menu==2){
			if(skills_ram==0){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>�������� �����<br>������� ������: "+sword+"<br>� ������ ������� ������ ���� �� ���� ����� ���������� �� 5%<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=8;
				gameX.mes.setText("<html>�������� �����<br>������� ������: "+sword+"<br>� ������ ������� ������ ���� �� ���� ����� ���������� �� 5%<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			if(skills_ram==5){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>�������� ������� ��������<br>������� ������: "+dual+"<br>� ������ ������� ������ ���� �� ���� ������� �������� ���������� �� 5%<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=9;
				gameX.mes.setText("<html>�������� ������� ��������<br>������� ������: "+dual+"<br>� ������ ������� ������ ���� �� ���� ������� �������� ���������� �� 5%<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			if(skills_ram==10){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>�������� �� ����<br>������� ������: "+bow+"<br>� ������ ������� ������ ���� �� �������� �� ���� ���������� �� 5%<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=10;
				gameX.mes.setText("<html>�������� �� ����<br>������� ������: "+bow+"<br>� ������ ������� ������ ���� �� �������� �� ���� ���������� �� 5%<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			if(skills_ram==15){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>�����<br>������� ������: "+magic+"<br>� ������ ������� ������ ���������� ���� ���������� �� 5%<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=11;
				gameX.mes.setText("<html>�����<br>������� ������: "+magic+"<br>� ������ ������� ������ ���������� ���� ���������� �� 5%<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			if(skills_ram==20){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>������� �����<br>������� ������: "+koja+"<br>� ������ ������� ������ �������� ���������� �� 3 ��., ���� ��������� � ������� ��� ���������� �� 1%<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=12;
				gameX.mes.setText("<html>������� �����<br>������� ������: "+koja+"<br>� ������ ������� ������ �������� ���������� �� 3 ��., ���� ��������� � ������� ��� ���������� �� 1%<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			if(skills_ram==25){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>�������� �����<br>������� ������: "+iron+"<br>� ������ ������� ������ �������� ���������� �� 5 ��.<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=13;
				gameX.mes.setText("<html>�������� �����<br>������� ������: "+iron+"<br>� ������ ������� ������ �������� ���������� �� 5 ��.<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			if(skills_ram==30){
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������� ������");
				if(exp==0){
				gameX.inMessage=3;
				gameX.mes.setText("<html>������� �����<br>������� ������: "+gold+"<br>� ������ ������� ������ �������� ���������� �� 3 ��., ���� ���������� �� 3 ��.<br>��������� ����� �������: "+exp+"</html>");	
				}
				else{
				gameX.inMessage=14;
				gameX.mes.setText("<html>������� �����<br>������� ������: "+gold+"<br>� ������ ������� ������ �������� ���������� �� 3 ��., ���� ���������� �� 3 ��.<br>��������� ����� �������: "+exp+"<br>�������� ������� ������?</html>");
				}
			}
			else if(skills_ram==1){
				skill("����������� �������","������ ����� ����� � ������������ 15% ����� ������� ������� ����",sword,1,0);
			}
			else if(skills_ram==2){
				skill("������ ����","�� ��������� �� 25% ������ ����� �� ���� �����",sword,10,0);
			}
			else if(skills_ram==3){
				skill("����������� ����","����� ����� ������� �������� ����� ���������� �� ����� �����",sword,20,0);
			}
			else if(skills_ram==4){
				skill("������������� �����","����� ����� ��������������� �������� � ������� "+vamp+"% �� ����������� �������� ���� �����",sword,30,0);
			}
			else if(skills_ram==6){
				skill("������� �����","����� ��������� � ������������ ������ ������",dual,1,1);
			}
			else if(skills_ram==7){
				skill("������ ������ �������","�� ��������� �� 25% ������ ����� �� ���� ������� ��������",dual,10,1);
			}
			else if(skills_ram==8){
				skill("������� ������������","�� ��������� �� 15% ������ ����� �� ���� ����� �������, ���� ������ ������ ������",dual,20,1);
			}
			else if(skills_ram==9){
				skill("����� �������","���������� �� 4-� �������� ������� ������������� �������� 20% ����� �� ���� ������� ��������",dual,30,1);
			}
			else if(skills_ram==11){
				skill("��� � ������","����� ������������ ����� - �� ��������� �������� ���� ����������� �� ����������. ��� �������� �� ���� ����������� ������� W,A,S,D",bow,1,2);
			}
			else if(skills_ram==12){
				skill("��������� �� �����","���� ��������� �� ��������� ����� ���������� �� 35%",bow,10,2);
			}
			else if(skills_ram==13){
				skill("���������� �������","����� ����� ������� ������ ���� � ������� ���",bow,20,2);
			}
			else if(skills_ram==14){
				skill("���������� ������","������� ������ �� ������ ����� ������",bow,30,2);
			}
			else if(skills_ram==16){
				skill("�������� ���","������� ������� W,A,S,D, �� ��������� ���, ����� ��������� �������� ���, ������� �� 3 ������ � ��������� 16 ��. ����� ������� ���������� ��� ������������ ������. ���������: 30 ��. ����",magic,1,3);
			}
			else if(skills_ram==17){
				skill("�������� ������","���� ������ ������ �����, ��� ������ ����� ������������� ������ 10 ��. �����.",magic,10,3);
			}
			else if(skills_ram==18){
				skill("������� �������","������� F, ����� ������� ����������� �� 4-� �������� ������� 10 ��. ����� � ������������ ��������, ������ �������� ����������� ���� ����������� �����. ���������� ���������� ���������� ���� ���. ���������: 45 ��. ����.",magic,20,3);
			}
			else if(skills_ram==19){
				skill("������� ����������","������� R, ����� ������� ����������� � ������� 2-� ������ 20 ��. �����. ���������: 90 ��. ����.",magic,30,3);
			}
			else if(skills_ram==21){
				skill("������ ����","����� ������������ ������� ������",koja,1,4);
			}
			else if(skills_ram==22){
				skill("������� �������","���� ��������� �� ����� � ������� ��� ���������� �� 15%. ����� �������� ������ ��� ������� ������� �����",koja,10,4);
			}
			else if(skills_ram==23){
				skill("������ ������� �����","���� ����� � ������� ��� ������� �� 50% ������ ����� ����������� � ������� ����� ��� ��� �����",koja,20,4);
			}
			else if(skills_ram==24){
				skill("������ �����","���� ����� ������� �� 35% ������ �����. ����� �������� ������ ��� ������� ������� �����",koja,30,4);
			}
			else if(skills_ram==26){
				skill("�������� �������","����� ������������ �������� ������",iron,1,5);
			}
			else if(skills_ram==27){
				skill("������������� �������","�� ��������� �� 25% ������ ����� �� ��������� ����. ����� �������� ������ ��� ������� �������� �����",iron,10,5);
			}
			else if(skills_ram==28){
				skill("������ �������� �����","���� ����� � ������� ��� ������� �� 40% ������ ����� ����������� � �������� ��� ������� �����",iron,20,5);
			}
			else if(skills_ram==29){
				skill("���������","���� ����� ��������� ����, ������ ��� �� ���������������� 5 ��. ��������. ����� �������� ������ ��� ������� �������� �����",iron,30,5);
			}
			else if(skills_ram==31){
				skill("������� �������","����� ������������ ������� ������",gold,1,6);
			}
			else if(skills_ram==32){
				skill("�������� ����","���� ���������� ������� �� 25% ������ ����. ����� �������� ������ ��� ������� ������� �����",gold,10,6);
			}
			else if(skills_ram==33){
				skill("�������� ����������","���� ����� ������� �� 35% ������ �����. ����� �������� ������ ��� ������� ������� �����",gold,20,6);
			}
			else if(skills_ram==34){
				skill("���������� �����","������ �������� ��������������� 30% �� ������������� ������ ����. ����� �������� ������ ��� ������� ������� �����",gold,30,6);
			}
		}
		mhp=100;
		mmp=100;
		if(eqarmor<=4){
			mhp+=koja*3;
		}
		else if(eqarmor<=9){
			mhp+=iron*5;
		}
		else if(eqarmor<=14){
			mhp+=gold*3;
			mmp+=gold*3;
		}
		if(hp>mhp){
			hp=mhp;
		}
		if(mp>mmp){
			mp=mmp;
		}
		gameX.hp=hp;
		gameX.mhp=mhp;
		gameX.mp=mp;
		gameX.mmp=mmp;
		damage();
		gameX.exp=exp;
	}
	public void start(String name, boolean IsCamp){
		gameX.animation=0;
		gameX.anima=1;
		gameX.texture=(int)(Math.random()*3);
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		getContentPane().setCursor(blankCursor);
		gameX.ram=true;
		hp=100; mhp=100; mp=100; mmp=100; herolevel=1; exp=0;
		map="";
		level="";
		profile="";
		menu=0;
		gameX.menu=0;
		gameX.inv_ram=0;
		gameX.skills_ram=0;
		gameX.hp=100;
		gameX.mhp=100;
		gameX.mp=100;
		gameX.mmp=100;
		spell="none";
		inv_ram = 0;
		skills_ram=0;
		gameX.inv_ram = 0;
		gameX.skills_ram=0;
		omg="";
		plusx=163;plusy=58;maxx=30;maxy=15;
		enemynear=false;
		Camp=IsCamp;
		active=true;
		setVisible(true);
		gameX.timerDraw.start();
		try(FileReader reader = new FileReader("./Pasan_Campaign/profile.pasan"))
        {
           // ������ �����������
            int d;
            while((d=reader.read())!=-1){
                 
               
            	profile=profile+((char)d);
            } 
            
        }
        catch(IOException ex){
             
            //System.out.println(ex.getMessage());
        }
		level+=profile.charAt(0);
		level+=profile.charAt(1);
		if (IsCamp==false){
			profreader();
		try(FileReader reader = new FileReader("./Pasan_UserMaps/"+name+".pasan"))
        {
           // ������ �����������
            int c;
            while((c=reader.read())!=-1){
                 
               
            	map=map+((char)c);
            } 
            
        }
        catch(IOException ex){
             
            //System.out.println(ex.getMessage());
        }
		setVisible(true);
		}
		
		else if (IsCamp==true){
			profreader();
			name="";
			name=name+profile.charAt(0);
			name=name+profile.charAt(1);
			if(Integer.parseInt(name)>=25){
				name="00";
				level="00";
				if(CampFinished<2){
					CampFinished+=1;
					gameX.CampFinished=CampFinished;
				}
			}
			if (CampFinished==2){
				gameX.inMessage=17;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(true);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>������ �� ������ ���� �������, ����� ������� �������� ������!<br>������ ������� ��� ����� (�� 0 �� 24).</html>");
				gameX.textField.setText("");
				gameX.textField.requestFocus();
				gameX.textField.addKeyListener(keylis1);
				
			}
			else if((CampFinished==0)&(Integer.parseInt(name)==0)){
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>����� ���������� � Pasan!<br>���� ���� - ��������� �� �������� �����, ���������� ������� ��������. ��� ������������ ����������� �������-�������. �� ������ ������������� ������ �� �������� ������.<br>������� ����!</html>");
			}
			else if((CampFinished==0)&(Integer.parseInt(name)==1)){
				gameX.inMessage=18;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>� �������� �� ������ ����� ��������� ������ � �����. ����� ������� ���������� ����������� ����, ��� ������. ����� ������� ���������, ������� ������� �.</html>");
			}
			else if((CampFinished==0)&(Integer.parseInt(name)==2)){
				gameX.inMessage=19;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>������ ��� ������������ �������� ��� ���� �������. �� ������ ������������ ��, ����� �������� ���� ������. �� 1, 10, 20 � 30 ������� ������ �� ���������� ����� ������. ������� � � ���� ���������, ����� ������� ���� �������.</html>");
			}
			else{
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("��������");
				gameX.mes.setText("<html>������� "+name+"</html>");
			}
			if((CampFinished==1)&(Integer.parseInt(name)==0)){
				eqweapon=0;
				gameX.eqweapon=0;
				eqarmor=0;
				gameX.eqarmor=0;
				for(int u=0;u<=4;u++){
					for(int o=0;o<=5;o++){
						inventory[u][o]=0;
					}
				}
				inventory[0][0]=1;
				inventory[0][3]=1;
				exp=0;
				sword=0;
				dual=0;
				bow=0;
				magic=0;
				koja=0;
				iron=0;
				gold=0;
				hp=100;
				mhp=100;
				mp=100;
				mmp=100;
				
				for(int u=0;u<=4;u++){
					for(int o=0;o<=5;o++){
						gameX.inv[u][o]=0;
					}
				}
				gameX.inv[0][0]=1;
				gameX.inv[0][3]=1;
				gameX.exp=0;
				gameX.sword=0;
				gameX.dual=0;
				gameX.bow=0;
				gameX.magic=0;
				gameX.koja=0;
				gameX.iron=0;
				gameX.gold=0;
				gameX.hp=100;
				gameX.mhp=100;
				gameX.mp=100;
				gameX.mmp=100;
				damage();
				profwriter();
			}
			if(CampFinished<2){
			try(FileReader reader = new FileReader("./Pasan_Campaign/"+name+".pasan"))
	        {
	           // ������ �����������
	            int c;
	            while((c=reader.read())!=-1){
	                 
	               
	            	map=map+((char)c);
	            } 
	            
	        }
	        catch(IOException ex){
	             
	            //System.out.println(ex.getMessage());
	        }  
			}
		}
		if((Camp==false)|(CampFinished<2)){
		i=0;
		n=0;
		int pow=100;
		if (Camp==true){
			pow+=CampFinished*xpow;
		}
		while(i<=149){
				omg=Character.toString(map.charAt(i+n));
			lol[i-i/15*15][i/15]=omg;
			gameX.lol[i-i/15*15][i/15]=omg;
			if(Integer.parseInt(omg)==0){hero=i;}
			if(Character.getNumericValue(map.charAt(i+n))==5){
				
				gmo = Character.getNumericValue(map.charAt(i+n+1));
				weapon[i-i/15*15][i/15]= gmo;
				gameX.weapon[i-i/15*15][i/15]= gmo;
				gmo = Character.getNumericValue(map.charAt(i+n+2));
				armor[i-i/15*15][i/15]= gmo;
				gameX.armor[i-i/15*15][i/15]=gmo;
				damage[i-i/15*15][i/15]= Character.getNumericValue(map.charAt(i+n+3))*100;
				damage[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+4))*10;
				damage[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+5));
				health[i-i/15*15][i/15]= Character.getNumericValue(map.charAt(i+n+6))*100;
				maxhealth[i-i/15*15][i/15]= Character.getNumericValue(map.charAt(i+n+6))*100;
				health[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+7))*10;
				maxhealth[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+7))*10;
				health[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+8));
				maxhealth[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+8));
				damage[i-i/15*15][i/15]=damage[i-i/15*15][i/15]*pow/100;
				health[i-i/15*15][i/15]=health[i-i/15*15][i/15]*pow/100;
				maxhealth[i-i/15*15][i/15]=maxhealth[i-i/15*15][i/15]*pow/100;
				gameX.damage[i-i/15*15][i/15]=damage[i-i/15*15][i/15];
				gameX.health[i-i/15*15][i/15]=health[i-i/15*15][i/15];
				gameX.maxhealth[i-i/15*15][i/15]=maxhealth[i-i/15*15][i/15];
				n+=8;
			}
			i+=1;
			}
		for (int p=0;p<5;p++){
			for(int o=0;o<6;o++){
				gameX.inv[p][o]=inventory[p][o];
			}
		}
		}
		rndclip=(int)(Math.random()*gameX.musicThread.kol);
		
		gameX.musicThread.load(rndclip);
		gameX.exp=exp;
	}
	public void loadMap(){
		try(FileReader reader = new FileReader("./Pasan_Campaign/"+name+".pasan"))
        {
           // ������ �����������
            int c;
            while((c=reader.read())!=-1){
                 
               
            	map=map+((char)c);
            } 
            
        }
        catch(IOException ex){
             
            //System.out.println(ex.getMessage());
        }  
		i=0;
		n=0;
		int pow=100;
		if (Camp==true){
			pow+=CampFinished*xpow;
		}
		while(i<=149){
				omg=Character.toString(map.charAt(i+n));
			lol[i-i/15*15][i/15]=omg;
			gameX.lol[i-i/15*15][i/15]=omg;
			if(Integer.parseInt(omg)==0){hero=i;}
			if(Character.getNumericValue(map.charAt(i+n))==5){
				
				gmo = Character.getNumericValue(map.charAt(i+n+1));
				weapon[i-i/15*15][i/15]= gmo;
				gameX.weapon[i-i/15*15][i/15]= gmo;
				gmo = Character.getNumericValue(map.charAt(i+n+2));
				armor[i-i/15*15][i/15]= gmo;
				gameX.armor[i-i/15*15][i/15]=gmo;
				damage[i-i/15*15][i/15]= Character.getNumericValue(map.charAt(i+n+3))*100;
				damage[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+4))*10;
				damage[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+5));
				health[i-i/15*15][i/15]= Character.getNumericValue(map.charAt(i+n+6))*100;
				maxhealth[i-i/15*15][i/15]= Character.getNumericValue(map.charAt(i+n+6))*100;
				health[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+7))*10;
				maxhealth[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+7))*10;
				health[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+8));
				maxhealth[i-i/15*15][i/15]+= Character.getNumericValue(map.charAt(i+n+8));
				damage[i-i/15*15][i/15]=damage[i-i/15*15][i/15]*pow/100;
				health[i-i/15*15][i/15]=health[i-i/15*15][i/15]*pow/100;
				maxhealth[i-i/15*15][i/15]=maxhealth[i-i/15*15][i/15]*pow/100;
				gameX.damage[i-i/15*15][i/15]=damage[i-i/15*15][i/15];
				gameX.health[i-i/15*15][i/15]=health[i-i/15*15][i/15];
				gameX.maxhealth[i-i/15*15][i/15]=maxhealth[i-i/15*15][i/15];
				n+=8;
			}
			i+=1;
			}
		for (int p=0;p<5;p++){
			for(int o=0;o<6;o++){
				gameX.inv[p][o]=inventory[p][o];
			}
		}
	}
	public void stop(){
		gameX.musicThread.closeall();
		active=false;
		setVisible(false);
		gameX.timerDraw.stop();
	}
	public void skill(String skillname,String desc,int skill,int lev, int y){
			skillyy=y;
			skillnum=skill;
			skilllev=lev;
			skillnamed=skillname;
			if(skill<lev){
			if(exp<lev-skill){
			gameX.inMessage=3;
			gameX.mes.setVisible(true);
			gameX.head.setVisible(true);
			gameX.textField.setVisible(false);
			gameX.head.setText(skillname);
			gameX.mes.setText("<html>"+desc+"<br>����������� ������� ������: "+lev+"<br>�� ������� ����� �������: "+((lev-skill)-exp)+".</html>");
			}
			else{
				gameX.inMessage=15;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText(skillname);
				gameX.mes.setText("<html>"+desc+"<br>��������� "+(lev-skill)+" ����� ������� ��� �������� ������?</html>");
			}
			}
			else{
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText(skillname);
				gameX.mes.setText("<html>"+desc+"<br>������ �������.</html>");
					
			}
		
			
	}
	public void missanim(){
		gameX.animation+=1;
		gameX.anim[gameX.animation][0]=5;
		gameX.anim[gameX.animation][1]=hero-hero/15*15;
		gameX.anim[gameX.animation][2]=hero/15;
	}
	public void swordattack(int start,int end){
		gameX.animation+=1;
		gameX.anim[gameX.animation][0]=8;
		gameX.anim[gameX.animation][1]=start;
		gameX.anim[gameX.animation][2]=end;
	}
	public void dualattack(int start,int end){
		gameX.animation+=1;
		gameX.anim[gameX.animation][0]=9;
		gameX.anim[gameX.animation][1]=start;
		gameX.anim[gameX.animation][2]=end;
	}
	public void bowattack(int start,int end){
		gameX.animation+=1;
		gameX.anim[gameX.animation][0]=1;
		gameX.anim[gameX.animation][1]=start;
		gameX.anim[gameX.animation][2]=end;
	}
	public void clearMessage(){
		gameX.inMessage=0;
		gameX.mes.setVisible(false);
		gameX.head.setVisible(false);
		gameX.textField.setVisible(false);
		gameX.head.setText("");
		gameX.mes.setText("");
		gameX.textField.setText("");
		requestFocus();
	}
	public void chestMes(String itemName){
		gameX.inMessage=3;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("���������");
		gameX.mes.setText("<html>�� �����:<br>"+itemName+".</html>");
	}
	public void chestMesDual(String itemName){
		gameX.inMessage=3;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("���������");
		gameX.mes.setText("<html>�� �����:<br>"+itemName+".<br>� ��� ��� ��� ��� ����� ����� ����!<br>�������� �� ��� ������ ������ � ���������.</html>");
	}
	public void invMes(String desc,int x, int y){
		invxx=x;
		invyy=y;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("����� ����������");
		if(inventory[x][y]>0){
		boolean canEquip=false;
		if(y==0){
			canEquip=true;
		}
		else if(y==1){
			if(dual>0){
				canEquip=true;
			}
		}
		else if(y==2){
			if(bow>0){
				canEquip=true;
			}
		}
		else if(y==3){
			if(koja>0){
				canEquip=true;
			}
		}
		else if(y==4){
			if(iron>0){
				canEquip=true;
			}
		}
		else if(y==5){
			if(gold>0){
				canEquip=true;
			}
		}
		if(canEquip==true){
			gameX.inMessage=6;
			gameX.mes.setVisible(true);
			gameX.head.setVisible(true);
			gameX.textField.setVisible(false);
			gameX.head.setText("����� ����������");
			gameX.mes.setText("<html>"+desc+"<br>� ���������: "+inventory[x][y]+".<br>�����������?</html>");
		}
		else{
			gameX.inMessage=3;
			gameX.mes.setVisible(true);
			gameX.head.setVisible(true);
			gameX.textField.setVisible(false);
			gameX.head.setText("����� ����������");
		gameX.mes.setText("<html>"+desc+"<br>� ���������: "+inventory[x][y]+".<br>������������� ������� ������.</html>");
			
		}
		}
		else if(y!=1){
		gameX.inMessage=3;
		gameX.mes.setVisible(true);
		gameX.head.setVisible(true);
		gameX.textField.setVisible(false);
		gameX.head.setText("����� ����������");
		gameX.mes.setText("<html>"+desc+"<br>� ��������� ��� ���� ����������.</html>");
			
		}
		else if(y==1){
			if((inventory[x][0]>=2)&(dual>0)){
				gameX.inMessage=7;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������ �������");
				gameX.mes.setText("<html>"+desc+"<br>���������� ��� ���� � ��� ������ ������?</html>");
			}
			else if(inventory[x][0]>=2){
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������ �������");
				gameX.mes.setText("<html>"+desc+"<br>������������� ������� ������.</html>");
			}
			else{
				gameX.inMessage=3;
				gameX.mes.setVisible(true);
				gameX.head.setVisible(true);
				gameX.textField.setVisible(false);
				gameX.head.setText("�������� ������ �������");
				gameX.mes.setText("<html>"+desc+"<br>����� ������� ������ ������, ����� ��� ���� ���� �� ������</html>");
			}
		}
		damage();
	}
	
}
