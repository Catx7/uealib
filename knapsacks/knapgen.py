import random, math, sys

def totalvalue(comb):
    total_weight = 0
    total_value = 0
    for item, weight, value in comb:
        total_weight += weight
        total_value += value
    return (total_value, -total_weight)

def knapsack01_dp(items, limit):
    table = [[0 for w in xrange(limit + 1)] for j in xrange(len(items) + 1)]
 
    for j in xrange(1, len(items) + 1):
        item, wt, val = items[j-1]
        for w in xrange(1, limit + 1):
            if wt > w:
                table[j][w] = table[j-1][w]
            else:
                table[j][w] = max(table[j-1][w],
                                  table[j-1][w-wt] + val)
 
    result = []
    w = limit
    for j in range(len(items), 0, -1):
        was_added = table[j][w] != table[j-1][w]
 
        if was_added:
            item, wt, val = items[j-1]
            result.append(items[j-1])
            w -= wt
 
    return result

if len(sys.argv) != 4:
    sys.exit('Usage: python knapgen.py N capacity name')

### CHANGE ME

def generate_item():
    """
    Must return (weight, value) pair
    """
    def get_rnd():
        return int(math.ceil(random.uniform(1, 10) * 10))
    return get_rnd(), get_rnd()

### / CHANGE ME

N = int(sys.argv[1])
capacity = int(sys.argv[2])
task_file = sys.argv[3] + ('.n%d.c%d' % (N, capacity))
solution_file = task_file + '.opt'

items = []
for x in xrange(N):
    item = generate_item()
    items.append(('item%d' %x, item[0], item[1]))

bagged = knapsack01_dp(items, capacity)

val, wt = totalvalue(bagged)
f = open(solution_file, 'w')
f.write("For a total value of %i and a total weight of %i:\n" % (val, -wt))
for i in range(len(items)):
    if items[i] in bagged:
        f.write('%d ' % i)
f.close()

f = open(task_file, 'w')
f.write('%d\n' % len(items))
f.write('%d\n' % capacity)
for i in items:
    f.write('%f %f\n' % (i[1], i[2]))
f.close()

print 'See "%s" and "%s" files for the result' % (task_file, solution_file)
