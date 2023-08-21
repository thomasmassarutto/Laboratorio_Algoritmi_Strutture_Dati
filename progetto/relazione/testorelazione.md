# Periodo frazionario
## Scopo del progetto
Lo scopo di questo progetto è implementare due algoritmi per il calcolo del _periodo frazionario minimo_ di una stringa e misurarne i tempi medi di esecuzione. Per _periodo frazionario minimo_ si intende, data una stringa _S_, la lunghezza (_p_) della sottostringa più breve che può essere ripetuta in modo da formare la stringa _S_. Deve quindi soddisfare la seguente proprietà:
$$s(i)=s(i+p) \quad \forall i= 1, \dots , n-p$$
 - $s$: stringa
 - $p$: lunghezza del periodo
 - $n$: lunghezza della stringa
 - $i$: parte esterna al periodo frazionario
## Algoritmi e fondamenti teorici
### PeriodNaive
_PeriodNaive_ è un algoritmo di complessità O(n^2^) che, mediante un ciclo _for_  che, iterando sulla stringa, ne analizza ogni possibile periodo di lunghezza _p_. La suddivisione della stringa avviene in due parti: _head_ e _tail_,  quando queste coincidono viene restituita la lunghezza _p_.
```
Funzione periodoNaive(Stringa):
	Length= lunghezza(Stringa)
	ITERA P da 1 fino Length{
		Head= sottostringa(Stringa, 0 -> Length-P )
		Hail= sottostringa(Stringa, P -> Length)
		
		SE Head è uguale a Tail:
			RESTITUISCI P	 
	} 
```
#### Calcolo di correttezza
#### Calcolo di complessità

Codice | Costo | Volte
-------- | ----- | -----
`public  static  int  periodNaive(String  stringa) { `| |
`int  n= stringa.length();` | $O(1)$| 1
`for (int  p=1; p<=n;p++){` | |
`String  head=stringa.substring(0, n-p);` |$O(n)$|n 
`String  tail=stringa.substring(p, n);` |$O(n)$|n
`if (head.equals(tail)){` |$O(p)$|n
`return  p;` |$O(1)$| 1
`}` ||
`}` ||
`return  ERR;`||
`}` ||

Dalla tabella risulta che il costo computazionale dell'algoritmo è:
$$
\begin {aligned}
Costo &= O(1) + O(n) \cdot n + O(n) \cdot n +O(p) \cdot n +O(1)\\
 &=n \cdot ( 2 \cdot O(n) +O(p) ) + 2 \cdot O(1)\\
 &\simeq O(n^2)
 \end {aligned}
$$
### PeriodSmart
_PeriodSmart_ è un algoritmo di complessità  O(n) che utilizza il concetto di _bordo_ per calcolare la lunghezza del _periodo frazionario minimo_. Genera un _array_ per memorizzare la lunghezza dei bordi delle sottostringhe (`r[i]` è il bordo della sottostringa che termina in posizione `i - 1`) e poi _itera_ sulla stringa cercando per ogni carattere un bordo fra i caratteri precedenti. Se il carattere corrente coincide col bordo, incrementa la lunghezza del bordo, altrimenti la riduce cercando un bordo più corto. Il periodo frazionario minimo è dato dalla lunghezza della stringa meno la lunghezza massima del bordo.
```
Funzione periodSmart(stringa):
    n = lunghezza(s)
    r = array di interi di dimensione n + 1
    r[1] = 0
    ITERA i da 2 fino a n:
        j = r[i - 1]
        FINCHE 	j > 0, 
		        carattere in posizione (i - 1) di s != dal carattere in posizione j di stringa:
            j = r[j]
        SE carattere in posizione (i - 1) di s == al carattere in posizione j di stringa:
            r[i] = j + 1
        ALTRIMENTI:
            r[i] = 0
    maxBordo = r[n]
    periodoFrazionario = n - maxBordo
    RESTITUISCI periodoFrazionario
```
#### Calcolo di correttezza
#### Calcolo di complessità
Codice | Costo | Volte
-------- | ----- | -----
`public  static  int  periodSmart(String  s) {`||
`int  n = s.length();`|$O(1)$|1
`int[] r = new  int[n + 1];`|$O(1)$|1
`r[1] = 0;`|$O(1)$|1
`for (int  i = 2; i <= n; i++) {`||
`int  j = r[i - 1];`|$O(1)$|n
`while (j > 0 && s.charAt(i - 1) != s.charAt(j)) {`|$O(2)$|n
`j = r[j];`|$O(1)$|n
`}`||
`if (s.charAt(i - 1) == s.charAt(j)) {`|$O(1)$|n
`r[i] = j + 1;`|$O(1)$|n
`} else {`||
`r[i] = 0;`|$O(1)$|n
`}}`||
`int  maxBordo = r[n];`|$O(1)$|1
`int  periodoFrazionario = n - maxBordo;`|$O(1)$|1
`return  periodoFrazionario;`|$O(1)$|1
`}`||

## Analisi delle prestazioni
Per valutare le prestazioni degli algoritmi in maniera empirica si è deciso di realizzare un programma Java in grado di misurare i tempi medi di esecuzione degli algoritmi. Il progetto si suddivide in 5 classi:
 - **Progetto**: classe principale che contiene i passaggi per testare, cronometrare e ricavare dati dagli algoritmi.
 - **Algos**: contiene l'implementazione degli algoritmi per il calcolo del periodo frazionario minimo di una stringa.
 - **Chrono**: fornisce meccanismi di cronometraggio necessari per misurare il tempo di esecuzione del programma e la stima di risoluzione del clock di sistema in nanosecondi.
 - **Logger**: gestisce la registrazione delle informazioni rilevanti su un file .csv.
 - **StringGenerator**: genera stringhe di test con varie lunghezze comprese fra 1000 e 500000. Queste hanno una distribuzione esponenziale e sono basate su un alfabeto ternario: _a_, _b_, _c_ 
### Metodologia di test
Una volta calcolato il _tempo minimo misurabile_, per ricavare dati attendibili sono state eseguite 6 run da 100 iterazioni ciascuna. Ad ogni iterazione una stringa generata casualmente, e progressivamente sempre più lunga, è stata fornita in input prima ad un algoritmo e poi ad un altro. È stato utilizzato un ciclo _while_ per iterare l'esecuzione dell'algoritmo e misurare il tempo trascorso, fino a quando tale tempo non è risultato superiore al _tempo minimo misurabile_. Il _tempo medio di esecuzione_ per una singola istanza di input è stato calcolato come il rapporto fra il tempo trascorso e il numero di iterazioni "_while_" eseguite.
```
Progetto main(): 
	t_min= tempo_minimo_misurabile()
	ITERA Run da 1 a 5{
		ITERA Tedt da 0 a 99{
			stringa= generatringa()
			//testa algoritmo 1
			iterazione= 0
			start= now()
			FAI{
				algoritmo(stringa)
				end= now()
				i++
			}FINCHÈ (end-start< tmin)
			tempo_medio= (end-start)/i
			// testa algoritmo 2
			...
		}
	} 
```
### Risultati sperimentali di vari test
Analizzando i grafici Durata vs Lunghezza della stringa si nota subito come i tempi di risoluzione dell'algoritmo _PeriodNaive_ crescano in maniera esponenziale, tanto da far sembrare nulli i tempi relativi a _PeriodSmart_.
### Riassunto dei dati
## Riferimenti
