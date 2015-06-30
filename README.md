### Implicit Context Propagation Using Object Algebras

The project is subdivided in two subprojects: 

* *macros*: Contains the implementation for the `lift` macro annotation.
* *core*: Contains the example of Featherweight Java (FJ) and Featherweight Java with State (SFJ). The language modules are defined as Object Algebras, and then lifted to allow trait composition. There is a manual implementation of the liftings, and an automated one, using the `lift` annotation.

To verify that everything works correctly, execute `sbt test`.
