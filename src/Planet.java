
/*Создать тип перечисления планет солнечной системы. Каждая планета характеризуется массой и радиусом. 
 * И для каждой планеты возможно рассчитать силу притяжения 
 * на поверхности (G*mass/R^2, где G - гравитационная постоянная).*/

import java.util.Scanner;

public class Planet {

	enum SolarSystem {
		MERCURY(3.33022E23, 2439.7), VENUS(4.8675E24, 6051.8), EARTH(5.9726E24, 6371.0), MARTH(6.4185E23,
				3389.5), JUPITER(1.8986E27, 69911), SATURN(5.6846E26,
						58232), URANUS(8.6832E25, 25362), NEPTUNE(1.0243E26, 24622), PLANETNINE(6E25, 17866);

		double mass;
		double radius;
		double res;
		final static double G = 6.67E-11;

		SolarSystem(double mass, double radius) {
			this.mass = mass;
			this.radius = radius;
		}

		public double gravity() {
			res = G * mass / (Math.pow(radius, 2));
			return res;
		}
	}

	public static void main(String[] args) {

		SolarSystem ss;

		for (SolarSystem s : SolarSystem.values()) {
			System.out.println(s + ": " + s.gravity());
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Введиде название планеты: ");
		String planetName = scanner.nextLine();
		scanner.close();

		for (SolarSystem s : SolarSystem.values()) {
			if (s.toString().equals(planetName)) {
				System.out.println(s.mass);
			}
		}
		
		
		ss = SolarSystem.valueOf(planetName);
		System.out.println(ss.mass);
	}
}
