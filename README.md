Basic inheritance example with json schema.
see [schema](src/main/resources/inheritance/schema.json)

Class diagram:
```
            ┌─────────────────────┐                   
            │                     │                   
            │  Peripheral         │                   
            │─────────────────────│                   
            │ getDriver(): Driver │                   
            └─────────────────────┘                   
                    ▲                           
                    │                          
                    │                           
      ┌─────────────┴────────────┐                      
┌─────┴────┐             ┌───────┴──────┐       
│  Mouse   │             │  Keyboard    │       
└──────────┘             └──────────────┘       
```

A list of abstract `peripherals` can be read from a [source json file](src/test/resources/inheritance/peripherals_valid.json) and mapped to the right instance, `mouse` or `keyboard`.
See jackson annotations in [Peripheral](src/main/java/com/github/sergiofgonzalez/examples/jackson/inheritance/Peripheral.java) class.