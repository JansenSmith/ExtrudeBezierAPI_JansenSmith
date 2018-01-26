import eu.mihosoft.vrl.v3d.svg.*;
import eu.mihosoft.vrl.v3d.Extrude;
File f = ScriptingEngine
	.fileFromGit(
		"https://gist.github.com/1606010b686494cea33527b71c98570a.git",//git repo URL
		"master",//branch
		"airFoil.svg"// File from within the Git repo
	)
SVGLoad s = new SVGLoad(f.toURI())
ArrayList<CSG>foil = s.extrude(-1,0.01)

CSG part = foil.get(0)
			.union(foil)
			.roty(90)
			.rotx(180)
			.toZMin()
			.toYMin()

ArrayList<CSG> parts = new ArrayList<CSG>()
int numParts = 20
for(int i=0;i<numParts;i++){
	double scale = (5+4*Math.cos(Math.PI*1*i/numParts)
					+0.1*Math.sin(Math.PI*30*i/numParts)
					)
	double twistAngle = Math.toDegrees(Math.cos(Math.PI*2*i/numParts))		
	//println scale 
	parts.add(
		part.scale(scale)
			.rotx(twistAngle)
	)
}

return Extrude.bezier(	parts,
					[(double)300.0,(double)0.0,(double)0.0], // Control point one
					[(double)200.0,(double)200.0,(double)50.0], // Control point two
					[(double)200.0,(double)200.0,(double)50.0] // Endpoint
					)