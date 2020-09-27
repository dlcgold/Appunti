cmake_minimum_required (VERSION 2.6)
project (es2)

set(CMAKE_BINARY_DIR ${CMAKE_SOURCE_DIR}/bin)
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_BINARY_DIR})
set(LIBRARY_OUTPUT_PATH ${CMAKE_BINARY_DIR})

add_executable(shift shift.cpp ordered_map.h ordered_hash.h shiftMethod.h shiftMethod.cpp)

