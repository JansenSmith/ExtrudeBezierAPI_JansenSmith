import eu.mihosoft.vrl.v3d.svg.*;
import eu.mihosoft.vrl.v3d.Extrude;
File f = ScriptingEngine
	.fileFromGit(
		"https://gist.github.com/1606010b686494cea33527b71c98570a.git",//git repo URL
		"master",//branch
		"airFoil.svg"// File from within the Git repo
	)
SVGLoad s = new SVGLoad(f.toURI())
ArrayList<CSG>foil = s.extrude(0.1,0.01)

CSG part = foil.get(0)
			.union(foil)
			.roty(90)
			.rotx(180)
			.toZMin()
			.toYMin()

ArrayList<CSG> parts = new ArrayList<CSG>()
int numParts = 30
for(int i=0;i<numParts;i++){
	double scale = (5+4*Math.cos(Math.PI*1*i/numParts)
					+0.1*Math.sin(Math.PI*30*i/numParts)
					)
	//println scale 
	parts.add(part.scale(scale)
	)
}

return Extrude.bezier(parts,[300,0,0],[200,200,-150],[200,200,50])