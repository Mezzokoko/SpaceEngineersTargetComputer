package spaceengineerstargetcomputer;

import static java.lang.Math.*;

public class SpaceEngineersTargetComputer
{
    public double v;
    public double d0;
    public double alpha;
    
    SpaceEngineersTargetComputer(double v, double d0, double dt, double t)
    {
        this.v = v;
        this.d0 = d0;
        alpha = toDegrees(acos( (d0*d0 + s(t)*s(t) - dt*dt ) / (2*d0*s(t)) ));
        //if (d0 < dt && !close) { alpha += 90.0; }
        System.out.println("beep");
    }
    
    SpaceEngineersTargetComputer(double v, double alpha, double d0)
    {
        this.v = v;
        this.alpha = alpha;
        this.d0 = d0;
    }
    
    public double s(double t)
    {
        return v*t;
    }
    
    public double d(double t)
    {
        return sqrt(d0*d0 + s(t) * s(t) - 2*d0*s(t)*cos(toRadians(alpha)));
    }
    
    /*public double beta(double t)
    {
        return toDegrees( asin( (sin(toRadians(alpha))*s(t)) / d(t) ) );
    }*/
    
    public double beta(double t)
    {
        return toDegrees(acos( (d0*d0+d(t)*d(t)-s(t)*s(t)) / (2*d0*d(t)) ));
    }
    
    public double attack_angle_1(double t, double traveltime)
    {
        return beta(t+traveltime) - beta(t);
    }
    
    public double attack_speed_1(double t, double traveltime)
    {
        return d(t+traveltime) / traveltime;
    }    
       
    public double attack_angle_2(double traveltime, double speed)
    {
        return beta(attack_time_2(traveltime,speed) + traveltime) - beta(attack_time_2(traveltime,speed));
    }
    
    public double attack_time_2(double traveltime, double speed)
    {
        return ((d0*cos(toRadians(alpha))+sqrt(d0*d0*cos(toRadians(alpha))*cos(toRadians(alpha))+speed*speed*traveltime*traveltime-d0*d0)) / v) - traveltime;
    }
    
    public double attack_angle_2_alt(double traveltime, double speed)
    {
        return beta(attack_time_2_alt(traveltime,speed) + traveltime) - beta(attack_time_2_alt(traveltime,speed));
    }
    
    public double attack_time_2_alt(double traveltime, double speed)
    {
        return ((d0*cos(toRadians(alpha))-sqrt(d0*d0*cos(toRadians(alpha))*cos(toRadians(alpha))+speed*speed*traveltime*traveltime-d0*d0)) / v) - traveltime;
    }
    
    //public double attack_speed_fromAngle(double t, double angle)
}