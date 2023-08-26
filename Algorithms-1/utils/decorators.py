import timeit


def performance_decorator(func):
    def wrapper(*args, **kwargs):
        start_time = timeit.default_timer()
        result = func(*args, **kwargs)
        elapsed = timeit.default_timer() - start_time
        print('Function {name} took {time} seconds to run.'.format(name=func.__name__, time=elapsed))
        return result
    return wrapper
