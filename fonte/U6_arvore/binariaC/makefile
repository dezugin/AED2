all: exec
 
exec: principal.o arvorebinaria.o no.o 
	gcc -o exec principal.o arvorebinaria.o no.o
 
principal.o: principal.c arvorebinaria.h no.h
	gcc -o principal.o principal.c -c -W -Wall -ansi -pedantic
 
arvorebinaria.o: arvorebinaria.c arvorebinaria.h no.h
	gcc -o arvorebinaria.o arvorebinaria.c -c -W -Wall -ansi -pedantic
 
no.o: no.c no.h
	gcc -o no.o no.c -c -W -Wall -ansi -pedantic

clean:
	rm -rf *.o *~ exec

limpa: 
	rm -rf *.o  