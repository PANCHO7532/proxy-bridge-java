# Proxy Bridge - Java Version
TCP Tunnel/Proxy for every transport purpose

## Building
1) Import this project under Eclipse
2) Run at least one time
3) Go to File > Export > Java > Runnable JAR File
4) Follow instructions and be sure to select MainClass as the main class (duh)
5) Select JAR location and click Finish

## Usage
```
Command Line:
java -jar [jarFile] -args -a ...

Argument summary:
[-dhost] Specifies the destination host where all the data should be forwarded
[-dport] Specifies the destination port on the destination host where Java should bind
[-mport] Specifies the local port where clients will start connecting
[-bufsize] Specifies an buffer size that will be used during traffic exchange
```

### Copyright P7COMunications LLC (c) 2021