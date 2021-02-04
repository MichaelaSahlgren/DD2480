# Decide
This project implements an hypothetical anti-ballisitic missile system as part of the course DD2480 - Software Engineering Fundamentals at KTH.

## Short overview
The program provides a class Decide with a method called decide(), found in Decide.java, which generates a signal that determines whether an interceptor should be launched or not based on radar traffic information.

The decision is made based on 15 Launch Interceptor Conditions, LIC. These 15 conditions forms a boolean Conditions met Vector, CMV, where each element in the vector corresponds to one LIC. This can be found in the file ConditionsMetVector.java where calculateRuleX() corresponds to LIC #X.

A matrix, the Logical Connector Matrix, LCM, then defines which LIC's must be considered in a joint way. The LCM is a 15x15 matrix with logical expressions on how the elements should be combined or if they should be discourage completely.

The combination of CMV and LCM is stored in a Preliminary Unlocking Matrix, PUM, which is a 15x15 matrix with boolean values.

A vector, the Preliminary unlocking Vector, PUV, represents which LIC's that actually matters in this particular decision. This is a boolean vector of size 15. The PUV together with the created PUM forms the Final Unlocking Vector, FUV, which also is a boolean vector with 15 elements.

A launch signal is generated if and only if all the elements in FUV are true.

## Built with
- Maven using [.mvn/wrapper](https://github.com/junit-team/junit5-samples/tree/r5.7.0/junit5-jupiter-starter-maven)
- [Travis-cli](https://travis-ci.com/ )

## Get Started
The repo can be cloned and run as it is. Maven does not need to be installed manually.

## Testing
The testing has been done using jUnit. All the tests cases can be found in src/test/java.

## Contributions
LIC = Launch Interceptor Condition

- Fredrik Norlin
  - Implemented method and testing for LIC0
  - Implemented method and testing for LIC5
  - Implemented method and testing for LIC10
  - Implemented create LogicalConnectorMatrix from textfile
  - Code review

- Frida Wallberg
  - Implemented method and testing for LIC1
  - Implemented method and testing for LIC6
  - Implemented method and testing for LIC11
  - Implemented ConditionsMetVector
  - Code review

- George Bassilious
  - Implemented method calculating angle between three points
  - Implemented method calculating distance between two points
  - Implemented method and testing for LIC2
  - Implemented method and testing for LIC7
  - Implemented method and testing for LIC12
  - Implemented FinalUnlockingVector
  - Implemented writing launch decision to standard output
  - Code review

- Michaela Sahlgren
  - Created GitHub repo
  - Created code skeleton
  - Implemented method and testing for LIC3
  - Implemented method and testing for LIC8
  - Implemented method and testing for LIC13
  - Implemented method calculating area between three points
  - Code review

- Sara Damne
  - Implemented method and testing for LIC4
  - Implemented method and testing for LIC9
  - Implemented method and testing for LIC14
  - Created README.md
  - Code review
