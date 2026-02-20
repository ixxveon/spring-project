/* ============================================================
   MeetHub — app.js
   data-action 기반 상호작용 (모바일 네비, 알림 닫기, 비번 토글, 폼 검증, 다크모드)
   ============================================================ */

(function () {
  'use strict';

  /* --------------------------------------------------------
     1) 모바일 네비 토글  [data-action="nav-toggle"]
     -------------------------------------------------------- */
  function initNavToggle() {
    var toggle = document.querySelector('[data-action="nav-toggle"]');
    var nav = document.getElementById('mobile-nav');
    if (!toggle || !nav) return;

    toggle.addEventListener('click', function () {
      var expanded = toggle.getAttribute('aria-expanded') === 'true';
      toggle.setAttribute('aria-expanded', String(!expanded));
      nav.classList.toggle('is-open');
      // 스크롤 잠금
      document.body.style.overflow = nav.classList.contains('is-open') ? 'hidden' : '';
    });

    // ESC 키로 닫기
    document.addEventListener('keydown', function (e) {
      if (e.key === 'Escape' && nav.classList.contains('is-open')) {
        toggle.setAttribute('aria-expanded', 'false');
        nav.classList.remove('is-open');
        document.body.style.overflow = '';
        toggle.focus();
      }
    });
  }

  /* --------------------------------------------------------
     2) 알림 / 토스트 닫기  [data-action="dismiss"]
     -------------------------------------------------------- */
  function initDismiss() {
    document.addEventListener('click', function (e) {
      var btn = e.target.closest('[data-action="dismiss"]');
      if (!btn) return;
      var alert = btn.closest('.alert');
      if (!alert) return;
      alert.classList.add('is-hiding');
      alert.addEventListener('animationend', function () {
        alert.remove();
      });
    });
  }

  /* --------------------------------------------------------
     3) 비밀번호 보기/숨기기  [data-action="toggle-password"]
     -------------------------------------------------------- */
  function initPasswordToggle() {
    document.addEventListener('click', function (e) {
      var btn = e.target.closest('[data-action="toggle-password"]');
      if (!btn) return;
      var wrap = btn.closest('.input-password-wrap');
      if (!wrap) return;
      var input = wrap.querySelector('input');
      if (!input) return;

      var isPassword = input.type === 'password';
      input.type = isPassword ? 'text' : 'password';
      btn.classList.toggle('is-visible', isPassword);
      btn.setAttribute('aria-label', isPassword ? '비밀번호 숨기기' : '비밀번호 보기');
    });
  }

  /* --------------------------------------------------------
     4) 가벼운 폼 검증  [data-validate="form"]
     -------------------------------------------------------- */
  function initFormValidation() {
    var forms = document.querySelectorAll('[data-validate="form"]');
    forms.forEach(function (form) {
      form.setAttribute('novalidate', '');

      form.addEventListener('submit', function (e) {
        var isValid = true;
        var groups = form.querySelectorAll('.form-group[data-rules]');

        groups.forEach(function (group) {
          var input = group.querySelector('.form-input, .form-select, .form-textarea');
          var errorEl = group.querySelector('.form-error');
          if (!input) return;

          var rules = group.getAttribute('data-rules').split(',');
          var value = input.value.trim();
          var errorMsg = '';

          rules.forEach(function (rule) {
            if (errorMsg) return; // 첫 오류만

            rule = rule.trim();
            if (rule === 'required' && value === '') {
              errorMsg = '필수 항목입니다.';
            }
            if (rule.indexOf('min:') === 0) {
              var minLen = parseInt(rule.split(':')[1], 10);
              if (value.length > 0 && value.length < minLen) {
                errorMsg = '최소 ' + minLen + '자 이상 입력하세요.';
              }
            }
            if (rule === 'email' && value !== '') {
              // 기본적인 이메일 패턴
              if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
                errorMsg = '올바른 이메일 형식이 아닙니다.';
              }
            }
          });

          if (errorMsg) {
            group.classList.add('has-error');
            if (errorEl) errorEl.textContent = errorMsg;
            isValid = false;
          } else {
            group.classList.remove('has-error');
            if (errorEl) errorEl.textContent = '';
          }
        });

        if (!isValid) {
          e.preventDefault();
          // 첫 오류 필드로 포커스
          var firstError = form.querySelector('.has-error .form-input, .has-error .form-select, .has-error .form-textarea');
          if (firstError) firstError.focus();
        }
      });

      // 입력 시 에러 제거
      form.addEventListener('input', function (e) {
        var group = e.target.closest('.form-group');
        if (group && group.classList.contains('has-error')) {
          group.classList.remove('has-error');
          var errorEl = group.querySelector('.form-error');
          if (errorEl) errorEl.textContent = '';
        }
      });
    });
  }

  /* --------------------------------------------------------
     5) 다크모드 토글  [data-action="theme-toggle"]
     -------------------------------------------------------- */
  function initThemeToggle() {
    var toggles = document.querySelectorAll('[data-action="theme-toggle"]');
    var html = document.documentElement;

    // 저장된 테마 복원
    var stored = localStorage.getItem('meethub-theme');
    if (stored) {
      html.setAttribute('data-theme', stored);
    } else if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
      html.setAttribute('data-theme', 'dark');
    }

    toggles.forEach(function (btn) {
      btn.addEventListener('click', function () {
        var current = html.getAttribute('data-theme');
        var next = current === 'dark' ? 'light' : 'dark';
        html.setAttribute('data-theme', next);
        localStorage.setItem('meethub-theme', next);
      });
    });
  }

  /* --------------------------------------------------------
     6) 탭 전환
     -------------------------------------------------------- */
  function initTabs() {
    var tabLists = document.querySelectorAll('[role="tablist"]');
    tabLists.forEach(function (list) {
      var tabs = list.querySelectorAll('[role="tab"]');
      tabs.forEach(function (tab) {
        tab.addEventListener('click', function () {
          // 모든 탭 비활성화
          tabs.forEach(function (t) {
            t.setAttribute('aria-selected', 'false');
          });
          tab.setAttribute('aria-selected', 'true');

          // 패널 전환
          var panelId = tab.getAttribute('aria-controls');
          var container = list.parentElement;
          var panels = container.querySelectorAll('[role="tabpanel"]');
          panels.forEach(function (p) {
            p.classList.remove('is-active');
          });
          var target = document.getElementById(panelId);
          if (target) target.classList.add('is-active');
        });

        // 키보드 탐색
        tab.addEventListener('keydown', function (e) {
          var idx = Array.from(tabs).indexOf(tab);
          var next;
          if (e.key === 'ArrowRight') next = tabs[(idx + 1) % tabs.length];
          if (e.key === 'ArrowLeft') next = tabs[(idx - 1 + tabs.length) % tabs.length];
          if (next) {
            e.preventDefault();
            next.click();
            next.focus();
          }
        });
      });
    });
  }

  /* --------------------------------------------------------
     7) 비밀번호 강도 표시
     -------------------------------------------------------- */
  function initPasswordStrength() {
    var input = document.getElementById('signup-password');
    var container = document.querySelector('.pw-strength');
    var textEl = document.querySelector('.pw-strength-text');
    if (!input || !container) return;

    var bars = container.querySelectorAll('.pw-strength-bar');

    input.addEventListener('input', function () {
      var val = input.value;
      var score = 0;
      if (val.length >= 8) score++;
      if (/[A-Z]/.test(val) && /[a-z]/.test(val)) score++;
      if (/[0-9]/.test(val) && /[^A-Za-z0-9]/.test(val)) score++;

      container.className = 'pw-strength';
      if (score >= 2) container.classList.add('level-' + score);

      bars.forEach(function (bar, i) {
        bar.classList.toggle('is-active', i < score);
      });

      var labels = ['', '약함', '보통', '강함'];
      if (textEl) textEl.textContent = val.length > 0 ? labels[score] || '약함' : '';
    });
  }

  /* --------------------------------------------------------
     8) 폼 검증 확장: 비밀번호 확인 일치 체크
     -------------------------------------------------------- */
  function initPasswordMatch() {
    var pw = document.getElementById('signup-password');
    var confirm = document.getElementById('signup-password-confirm');
    if (!pw || !confirm) return;

    confirm.addEventListener('input', function () {
      var group = confirm.closest('.form-group');
      var errorEl = group ? group.querySelector('.form-error') : null;
      if (pw.value && confirm.value && pw.value !== confirm.value) {
        if (group) group.classList.add('has-error');
        if (errorEl) errorEl.textContent = '비밀번호가 일치하지 않습니다.';
      } else {
        if (group) group.classList.remove('has-error');
        if (errorEl) errorEl.textContent = '';
      }
    });
  }

  /* --------------------------------------------------------
     Init
     -------------------------------------------------------- */
  document.addEventListener('DOMContentLoaded', function () {
    initNavToggle();
    initDismiss();
    initPasswordToggle();
    initFormValidation();
    initThemeToggle();
    initTabs();
    initPasswordStrength();
    initPasswordMatch();
  });
})();
