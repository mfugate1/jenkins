@NonCPS
List call(List versions) {
    return versions.sort(false) { a, b -> 

        List verA = a.tokenize('.')
        List verB = b.tokenize('.')
    
        def commonIndices = Math.min(verA.size(), verB.size())
    
        for (int i = 0; i < commonIndices; ++i) {
            def numA = verA[i].toInteger()
            def numB = verB[i].toInteger()
        
            if (numA != numB) {
                return numA <=> numB
            }
        }
    
        // If we got this far then all the common indices are identical, so whichever version is longer must be more recent
        verA.size() <=> verB.size()
    }
}