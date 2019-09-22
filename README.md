# XML_Converter
Assignment from Softhouse

Integration between two old systems.

One system delivers a row-based file format while the other demands XML. Write a converter that builds the correct XML structure. 
Use any programming language! (except C / C ++)

P|firstname|lastname  
T|mobile|landline  
A|street|city|postcode  
F|name|yearofbirth  

P can be followed by T, A and F  
F can be followed by T and A  

Example:

P|Carl Gustaf|Bernadotte  
T|0768-101801|08-101801  
A|Drottningholms slott|Stockholm|10001  
F|Victoria|1977  
A|Haga Slott|Stockholm|10002  
F|Carl Philip|1979  
T|0768-101802|08-101802  
P|Barack|Obama  
A|1600 Pennsylvania Avenue|Washington, D.C  

Provides XML as:

```xml
<people>  
  <person>  
    <firstname>Carl Gustaf</firstname>  
    <lastname>Bernadotte</lastname>
    <address>
      <street>Drottningholms slott</street>
      ...
     </address>
    <phone>
      <mobile>0768-101801</mobile>
      ...
    </phone>
    <family>
      <name>Victoria</name>
        <born>1977</born>
        <address>...</address>
     </family>
     <family>...</family>
  </person>
  <person>...</person>
</people>
```
