### Implicit Context Propagation Using Object Algebras

The project is subdivided in two subprojects: 

* Macros: Contains the implementation for the `liift` macro annotation.
* Core: Contains the example of Featherweight Java (FJ) and Featherweight Java with State (SFJ). The language modules are defined as Object Algebras, and then lifted to allow trait composition. There is a manual implementation of the liftings, and an automated one, using the `lift` annotation.

To verify that everything works fine, execute `sbt test`.
