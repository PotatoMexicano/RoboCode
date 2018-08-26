package carniceiro;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * FirstRobot - a robot by (your name here)
 */
public class carniceiro extends Robot
{

	public void run() {
		setColors(Color.BLUE, Color.RED, Color.GRAY, Color.WHITE, Color.GREEN);		

		while(true) {
		turnRadarRight(360);
	     ahead(100);
	     turnGunRight(360);
	     back(100);
		
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		double max = 100;
			
      if(e.getEnergy() < max){
         max = e.getEnergy();
         miraCanhao(e.getBearing(), max, getEnergy());
      }else if(e.getEnergy() >= max){
         max = e.getEnergy();
         miraCanhao(e.getBearing(), max, getEnergy());
      }else if(getOthers() == 1){
         max = e.getEnergy();
         miraCanhao(e.getBearing(), max, getEnergy());
      }
	}

	public void onHitByBullet(HitByBulletEvent e) {
				
		turnLeft(90);
	    back(100); 
	}
	
	
	public void onHitWall(HitWallEvent e) {
			turnLeft(90);
      		ahead(200);
	}	
	  public void onHitRobot(HitRobotEvent e) {
	   tiroFatal(e.getBearing(), e.getEnergy(), getEnergy());	
	
    }
	public void miraCanhao(double PosIni, double energiaIni, double minhaEnergia) {
       double Distancia = PosIni;
	   double Coordenadas = getHeading() + PosIni - getGunHeading();
	   double PontoQuarenta = (energiaIni / 4) + .1;
	   if (!(Coordenadas > -180 && Coordenadas <= 180)) {
	      while (Coordenadas <= -180) {
		     Coordenadas += 360;
		  }
		  while (Coordenadas > 180) {
		     Coordenadas -= 360;
		  }
	   }
	   turnGunRight(Coordenadas);
		
	   if (Distancia > 200 || minhaEnergia < 15 || energiaIni > minhaEnergia){
          fire(1);
       } else if (Distancia > 50 ) {
          fire(2);
       } else {
          fire(PontoQuarenta);
       }
   }
		public void tiroFatal(double PosIni, double energiaIni, double minhaEnergia) {
     // double Distancia = PosIni;
	  double Coordenadas = getHeading() + PosIni - getGunHeading();
	  double PontoQuarenta = (energiaIni / 4) + .1;
	  if (!(Coordenadas > -180 && Coordenadas <= 180)) {
	     while (Coordenadas <= -180) {
	        Coordenadas += 360;
		 }
		 while (Coordenadas > 180) {
	        Coordenadas -= 360;
	     }
	  }
	  turnGunRight(Coordenadas);
	  fire(PontoQuarenta);
       
   }
}
