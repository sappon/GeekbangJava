Q4 写一段对于不同 GC 和堆内存的总结

##串行 GC (Serial GC)

-XX:+UseSerialGC

	- single thread Stop-The-World (STW)
	
	- **mark-copy** for young generation
	
	- **mark-sweep-compact** for old generation
	
	- high CPU usage, long pause, suitable for small Heap size and single-core CPU
	


##Parallel GC

-XX:+UseParallelGC

-XX:+UseParallelOldGC

	- multiple thread Stop-The-World (STW)
	
	- **mark-copy** for young generation
	
	- **mark-sweep-compact** for old generation
	
	- shorter pause time than Serial GC
	
	- no GC process if not during GC starts
	
	

##CMS GC (Mostly Concurrent Mark and Sweep GC)

-XX:+UseConcMarkSweepGC

	- **mark-copy** for young generation
	
	- **mark-sweep** for old generation, running concurrent with application thread
	
	- no obvious pause (only STW for stage 1: Initial mark and stage 4: Final remark), slower processing rate
	
	

##G1 GC (Garbage first)

-XX:+UseG1GC -XXMaxGCPauseMillis=50

	- objective is to control the STW pausing time within expected value
	
	- break heap into segments, and mark the segments as Eden, Survivor or Old
	
	- clean the segments that has most garbage
	
	- **mark-sweep+copy** for segments



#In HEAP

##Young Generation

- Serial

- ParNew

- Parllel (Scavenge)



##Old Generation

- Serial Old

- Parallel Old

- CMS
