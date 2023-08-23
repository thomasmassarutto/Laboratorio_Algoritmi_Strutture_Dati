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

_PeriodNaive_ è un algoritmo di complessità $O(n^2)$ che, mediante un ciclo _for_, itera su di una stringa, e ne analizza ogni possibile periodo. Partendo da un periodo di lunghezza _p=1_ e incrementando di uno ogni volta il valore, la stringa viene suddivisa in due parti: _head_ e _tail_. Quando queste coincidono l'esecuzione termina e viene restituita la lunghezza finale _p_.

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

#### Dimostrazione di correttezza

##### Caso base: $P(1)$

Il _caso base_ è quando la lunghezza del periodo è $p=1$. In questa situazione `head` e `tail` sono stringhe vuote, quindi il successivo test di uguaglianza risulta positivo e l'algoritmo ritorna l'indice di iterazione $1$.  

##### Passo induttivo: $\forall p \ P(p) \Rightarrow P(p+1)$

Nel caso in cui il periodo sia $p>1$ supponiamo come _ipotesi induttiva_ che il programma funzioni correttamente fino a $k>1$. Nel caso $P(k+1)$ `head` contiene tutti i caratteri esclusi gli ultimi $k+1$, mentre `tail` contiene tutti i caratteri da $k+1$ in poi. Se all'iterazione $p=k+1$ `head` e `tail` sono uguali il test di uguaglianza restituirà il periodo $p=k+1$.

##### Conclusione dimostrazione

Abbiamo appena dimostrato che il programma funziona correttamente sia nel _caso base_ sia nel _caso indutivo_.

#### Calcolo di complessità

| Codice                                                 | Costo  | Volte |
| ------------------------------------------------------ | ------ | ----- |
| `public  static  int  periodNaive(String  stringa) { ` |        |
| `int  n= stringa.length();`                            | $O(1)$ | 1     |
| `for (int  p=1; p<=n;p++){`                            |        |
| `String  head=stringa.substring(0, n-p);`              | $O(n)$ | n     |
| `String  tail=stringa.substring(p, n);`                | $O(n)$ | n     |
| `if (head.equals(tail)){`                              | $O(p)$ | n     |
| `return  p;`                                           | $O(1)$ | 1     |
| `}`                                                    |        |
| `}`                                                    |        |
| `return  ERR;`                                         |        |
| `}`                                                    |        |

Dalla tabella risulta che il costo computazionale dell'algoritmo è:

$$
\begin {aligned}
Costo &= O(1) + O(n) \cdot n + O(n) \cdot n +O(p) \cdot n +O(1)\\
 &=n \cdot ( 2 \cdot O(n) +O(p) ) + 2 \cdot O(1)\\
 &\simeq O(n^2)
 \end {aligned}
$$

### PeriodSmart

_PeriodSmart_ è un algoritmo di complessità $O(n)$ che utilizza il concetto di _bordo_ per calcolare la lunghezza del _periodo frazionario minimo_. Genera un _array_ per memorizzare la lunghezza dei bordi delle sottostringhe (`r[i]` è il bordo della sottostringa che termina in posizione `i - 1`) e poi _itera_ sulla stringa cercando per ogni carattere un bordo fra i caratteri precedenti. Se il carattere corrente coincide col bordo, incrementa la lunghezza del bordo, altrimenti la riduce cercando un bordo più corto. Il periodo frazionario minimo è dato dalla lunghezza della stringa meno la lunghezza massima del bordo.

```
Funzione periodSmart(S: stringa):
    N = lunghezza(s)
    R = array di interi di dimensione n + 1
    R[1] = 0
    ITERA I da 2 fino a N:
        J = R[I - 1]
        FINCHE 	J > 0,
		        carattere in (i - 1) di S != dal carattere in posizione J di S:
            J = R[J]
        SE carattere in (I - 1) di S == al carattere in J di s:
            R[I] = J + 1
        ALTRIMENTI:
            R[I] = 0
    MaxBordo = R[N]
    PeriodoFrazionario = N - MaxBordo
    RESTITUISCI PeriodoFrazionario
```

#### Dimostrazione di correttezza

##### Caso base $P(1)$

Il _caso base_ prevede che la lunghezza della stringa e del periodo sia $p=1$. In questa situazione il periodo frazionario è definito come la lunghezza della stringa meno il bordo massimo, ovvero, in questo caso, il valore dell'array in posizione `r[1]`. Il programma ritorna $1$ come periodo.

##### Passo induttivo $\forall p \ P(p) \Rightarrow P(p+1)$

Nel caso in cui $p>1$ supponiamo come _ipotesi induttiva_ che il programma funzioni correttamente fino a $k>1$. Se `s[i - 1]` è uguale a `s[r[i - 1]]`, allora `r[i]` viene impostato uguale a `r[i - 1] + 1`, aggiornando cosi la lunghezza del bordo.

Se `s[i - 1]` è diverso da `s[r[i - 1]]`, l'algoritmo entra nel ciclo `while`. Qui, `r[i]` verrà ridotto al valore di `r[r[i - 1]]`, che rappresenta il bordo più breve.

Alla fine del programma, viene calcolato il periodo frazionario come lunghezza della stringa meno lunghezza massima del bordo.

##### Conclusione dimostrazione

Abbiamo appena dimostrato che il programma funziona correttamente sia nel _caso base_ sia nel _caso indutivo_.

#### Calcolo di complessità

| Codice                                              | Costo  | Volte |
| --------------------------------------------------- | ------ | ----- |
| `public  static  int  periodSmart(String  s) {`     |        |
| `int  n = s.length();`                              | $O(1)$ | 1     |
| `int[] r = new  int[n + 1];`                        | $O(1)$ | 1     |
| `r[1] = 0;`                                         | $O(1)$ | 1     |
| `for (int  i = 2; i <= n; i++) {`                   |        |
| `int  j = r[i - 1];`                                | $O(1)$ | n     |
| `while (j > 0 && s.charAt(i - 1) != s.charAt(j)) {` | $O(2)$ | n     |
| `j = r[j];`                                         | $O(1)$ | n     |
| `}`                                                 |        |
| `if (s.charAt(i - 1) == s.charAt(j)) {`             | $O(1)$ | n     |
| `r[i] = j + 1;`                                     | $O(1)$ | n     |
| `} else {`                                          |        |
| `r[i] = 0;`                                         | $O(1)$ | n     |
| `}}`                                                |        |
| `int  maxBordo = r[n];`                             | $O(1)$ | 1     |
| `int  periodoFrazionario = n - maxBordo;`           | $O(1)$ | 1     |
| `return  periodoFrazionario;`                       | $O(1)$ | 1     |
| `}`                                                 |        |

Dalla tabella risulta che il costo computazionale dell'algoritmo è:

$$
\begin {aligned}
Costo &= O(1) + O(1) + O(1) + O(1) \cdot n + O(2) \cdot n + O(1) \cdot n + O(1) \cdot n + O(1) \cdot n + O(1) \cdot n  + O(1) + O(1) + O(1) \\
 &= 6 \cdot O(1) + n \cdot O(1) + O(2)\\
 &\simeq O(n)
 \end {aligned}
$$

## Analisi delle prestazioni

Per valutare le prestazioni degli algoritmi in maniera empirica si è deciso di realizzare un programma Java in grado di misurare i tempi medi di esecuzione degli algoritmi. Il progetto si suddivide in 5 classi:

- **Progetto**: classe principale che contiene i passaggi per testare, cronometrare e ricavare dati utili in fase di analisi.
- **Algos**: contiene l'implementazione degli algoritmi per il calcolo del periodo frazionario minimo di una stringa.
- **Chrono**: fornisce meccanismi di cronometraggio necessari per misurare il tempo di esecuzione del programma e la stima di risoluzione del clock di sistema in nanosecondi.
- **Logger**: gestisce la registrazione delle informazioni rilevanti su un file .csv.
- **StringGenerator**: genera stringhe casuali con varie lunghezze comprese fra 1000 e 500000. Queste hanno una distribuzione esponenziale e sono basate su un alfabeto ternario: _a_, _b_, _c_.

### Metodologia di test

Una volta calcolato il _tempo minimo misurabile_, per ricavare dati attendibili sono state eseguite 6 run da 100 iterazioni ciascuna. Ad ogni iterazione una stringa generata casualmente, e progressivamente sempre più lunga, è stata fornita in input ad entrambi gli algoritmi. È stato utilizzato un ciclo _while_ per iterare l'esecuzione dell'algoritmo e misurare il tempo trascorso, fino a quando tale tempo non è risultato superiore al _tempo minimo misurabile_. Il _tempo medio di esecuzione_ per una singola istanza di input è stato calcolato come il rapporto fra il tempo trascorso e il numero di iterazioni "_while_" eseguite.

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
				iterazione++
			}FINCHÈ (end-start< tmin)
			tempo_medio= (end-start)/i
			// testa algoritmo 2
			...
		}
	}
```

### Risultati sperimentali di vari test

Analizzando i grafici _Durata_ vs _Lunghezza della stringa_ si nota come, i tempi di risoluzione relativi all'algoritmo _PeriodNaive_ crescono esponenzialmente, mentre,  quelli relativi a _PeriodSmart_ hanno una crescita lineare.

Sin dalle prime iterazioni il delta temporale fra i due algoritmi è dell'ordine di qualche millisecondo. Questa questa differenza aumenta esponenzialmente raggiungendo oltre mezzo secondo nelle iterazioni finali.

Basandosi sui dati raccolti, è possibile creare un modello in grado di prevedere l'andamento temporale dei due algoritmi. In particolare l'evoluzione del modello Naive può essere descritta da un'equazione di secondo grado:

$$
y_{naive} = 565335.910 + 142.414 \cdot x + 0.146 \cdot x^2
$$

La validità del modello è supportata dagli indici statistici, in particolare il _Residual Standard Error_ indica come l'errore standard dei residui sia di circa 13.73 millisecondi. Gli indici _Multiple R-squared_ e _Adjusted R-squared_, atti a spiegare la variabilità dei dati,hanno totalizzato il valore massimo di 1. Infine il _p-value_ è di gran lunga inferiore alla soglia di 0.05 ($2,2 \times 10^{-16}$), il che indica un risultato statisticamente significativo. 

L'evoluzione del modello Smart può essere, invece, descritta da un'equazione di primo grado:
$$
y_{smart} = 10679.087 + 6.607 \cdot x
$$

Anche in questo caso la validità del modello è supportata dagli indici statistici, in particolare il _Residual Standard Error_ indica come l'errore standard dei residui sia di circa 0,0448 millisecondi. Gli indici _Multiple R-squared_ e _Adjusted R-squared_,hanno totalizzato il valore quasi massimo di 0.9969. Infine il _p-value_ è di gran lunga inferiore alla soglia di 0.05 ($2,2 \times 10^{-16}$), il che indica un risultato statisticamente significativo.

## Riferimenti
