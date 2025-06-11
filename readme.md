# Simulador Tomasulo

Este é um simulador do algoritmo de Tomasulo implementado em Java.

## Sequência de Instruções

O simulador executa a seguinte sequência de instruções:

1. `ADD R0 1 2`
2. `ADD R1 R0 7`
3. `ADD R2 2 2`
4. `SUB R3 R2 1`
5. `ADD R4 5 16`
6. `ADD R4 R1 R3`
7. `MUL R0 5 6`
8. `LD R1 1 0`
9. `ADD R2 R0 R1`
10. `STR R2 0 0`

## Configuração do Sistema

O simulador possui as seguintes configurações:

- 5 registradores (R0-R4)
- 3 estações de reserva para adição/subtração
- 2 estações de reserva para multiplicação/divisão
- 10 posições de memória (M0-M9)
- Tempos de execução:
  - Adição/Subtração: 2 ciclos
  - Multiplicação/Divisão: 10 ciclos
  - Load/Store: 2 ciclos

## Formato da Saída

A saída do simulador é dividida em seções:

1. **Clock**

   ```
   Clock: X
   ```

   Mostra o ciclo atual do simulador

2. **Instruction Queue**

   ```
   Instruction Queue: [instrução1, instrução2, ...]
   ```

   Lista de instruções pendentes

3. **Reservation Station**

   ```
   Reservation Station:
   ADD0: op v1 v2 dest
   ADD1: op v1 v2 dest
   ADD2: op v1 v2 dest
   MUL0: op v1 v2 dest
   MUL1: op v1 v2 dest
   ```

   Estado das estações de reserva:

   - `op`: operação (ADD, SUB, MUL, DIV)
   - `v1, v2`: operandos
   - `dest`: registrador de destino

4. **Register**

   ```
   Register:
   R0: valor busy empty
   R1: valor busy empty
   R2: valor busy empty
   R3: valor busy empty
   R4: valor busy empty
   ```

   Estado dos registradores:

   - `valor`: valor atual
   - `busy`: 1 se ocupado, 0 se livre
   - `empty`: 1 se vazio, 0 se não

5. **Memory**
   ```
   Memory:
   M0: valor
   M1: valor
   ...
   M9: valor
   ```
   Conteúdo da memória

## Exemplo de Execução

```
Clock: 1
Instruction Queue: [ADD R0 1 2, ADD R1 R0 7, ...]
Reservation Station:
ADD0: ADD 1 2 R0
ADD1: null null null null
ADD2: null null null null
MUL0: null null null null
MUL1: null null null null
Register:
R0: 0 1 0
R1: 0 0 1
R2: 0 0 1
R3: 0 0 1
R4: 0 0 1
Memory:
M0: 10
M1: 20
...
M9: 100
```

## Como Executar

1. Compile o projeto:

   ```
   javac models/*.java
   ```

2. Execute o simulador:
   ```
   java models.TomasuloSimulator
   ```
