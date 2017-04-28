import eu.mihosoft.vrl.v3d.Extrude;


CSG part = new Cube(10).toCSG()

ArrayList<CSG> parts = new ArrayList<CSG>()
int numParts = 10
for(int i=0;i<numParts;i++){	
	//println scale 
	parts.add(part.clone())
}
// A full bezier extrud with the gaps between the parts filled with a convex hull of the part
def fullBezier =  Extrude.bezier(	parts,
					[0,0,300], // Control point one
					[200,200,-150], // Control point two
					[200,200,300] // Endpoint
					)
// thake the parts and just move to locations along the bezier curve
def movedCubesBezier =  Extrude.moveBezier(	parts,
					[0,0,300], // Control point one
					[200,200,-150], // Control point two
					[200,200,300] // Endpoint
					).collect{
						it.movey(200)							
					}
// This is for creating a set of frame transformations from the bezier parameters
// this can be used for complex part creation or for path planning
ArrayList<Transform> transforms = 	Extrude.bezierToTransforms(	[0,0,300], // Control point one
													[200,200,-150], // Control point two
													[200,200,300], // Endpoint
													numParts// Iterations
					)			
fullBezier.addAll(movedCubesBezier)

return fullBezier