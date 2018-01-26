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
					[(double)0.0,(double)0.0,(double)300.0], // Control point one
					[(double)200.0,(double)200.0,(double)-150.0], // Control point two
					[(double)200.0,(double)200.0,(double)300.0]// Endpoint
					)

// thake the parts and just move to locations along the bezier curve
def movedCubesBezier =  Extrude.moveBezier(	parts,
					[(double)0,(double)0,(double)300], // Control point one
					[(double)200,(double)200,(double)-150], // Control point two
					[(double)200,(double)200,(double)300] // Endpoint
					).collect{
						it.movey(200)							
					}
// This is for creating a set of frame transformations from the bezier parameters
// this can be used for complex part creation or for path planning
ArrayList<Transform> transforms = 	Extrude.bezierToTransforms(	new Vector3d(0,(double)0,(double)300), // Control point one
													new Vector3d((double)200,(double)200,(double)-150), // Control point two
													new Vector3d((double)200,(double)200,(double)300), // Endpoint
													numParts// Iterations
					)			
fullBezier.addAll(movedCubesBezier)

return fullBezier