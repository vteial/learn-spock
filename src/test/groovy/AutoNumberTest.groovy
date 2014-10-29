

def an = new AutoNumber()
an.id = 'testKey'
an.value = 1
println an

AutoNumberService ans = new MemoryAutoNumberService()
println ans.nextNumber('testId')
println ans.nextNumber('testId')
