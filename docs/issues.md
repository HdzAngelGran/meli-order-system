# Issues and Resolutions

This document tracks issues encountered during development and the solutions applied, to facilitate learning and continuous improvement.

## 1. MockitoException with Static Mocks in Tests

**Issue:**
An `org.mockito.exceptions.base.MockitoException` was thrown when running tests in `OrderServiceImplTest.java`. The error message was: `For mx.arkn37.meli.mappers.OrderMapper, static mocking is already registered in the current thread`.

This occurred because multiple tests were creating a static mock for the `OrderMapper` class, but the mock was not being properly closed or deregistered after each test. This led to a conflict where a new test would try to register a mock that was already active from a previous test.

**Resolution:**
The issue was resolved by ensuring that the static mock was properly managed within the scope of each test where it was used. Instead of simply creating the mock, it was wrapped in a `try-with-resources` block.

**Example:**
```java
// Before
mockStatic(OrderMapper.class).when(() -> OrderMapper.toResponse(order)).thenReturn(response);
OrderResponse result = orderServiceImpl.orderByUuid(uuid);
assertThat(result).isEqualTo(response);

// After (Corrected)
try (var mocked = mockStatic(OrderMapper.class)) {
    mocked.when(() -> OrderMapper.toResponse(order)).thenReturn(response);
    OrderResponse result = orderServiceImpl.orderByUuid(uuid);
    assertThat(result).isEqualTo(response);
}
```

By using `try-with-resources`, the `MockedStatic` instance (`mocked`) is automatically closed at the end of the block. This deregisters the static mock, preventing any conflicts with subsequent tests and ensuring each test runs in a clean, isolated environment.
