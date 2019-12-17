#!/bin/bash
add_recursively () {
    local n=$1
    local sum=${2:-0}
    if (( n == 0 )); then
        echo $sum
        return
    fi
    $FUNCNAME $((n - 1)) $((sum + n))
}
y=$1
exit $(add_recursively $y)
